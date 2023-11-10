package com.twoclothing.redismodel.itembrowsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.twoclothing.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ItemBrowsingJedisDAO implements ItemBrowsingDAO {
	private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

	private static final int REDIS_NUMBER = 12;

	private static final String MBR_PREFIX = "mbrId:";

	private static final String ITEM_PREFIX = "itemId:";

	private static final String MBR_SUFFIX = ":itemView";

	private final int TTL = 30 * 24 * 60 * 60;

	private final int MAX_VIEW = 100;

	@Override
	public void insert(ItemBrowsing itemBrowsing) {
		try (Jedis jedis = jedisPool.getResource()) {
			jedis.select(REDIS_NUMBER);

			long browsingTime = itemBrowsing.getBrowsingTime();
			String key = MBR_PREFIX + itemBrowsing.getMbrId() + MBR_SUFFIX;
			String value = ITEM_PREFIX + itemBrowsing.getItemId();
			jedis.zadd(key, (double) browsingTime, value);
			jedis.expire(key, TTL);

			Long valueSize = jedis.zcard(key);
			if (valueSize > MAX_VIEW) {
				jedis.zremrangeByRank(key, 0, valueSize - MAX_VIEW - 1);
			}
		}
	}

	@Override
	public List<Integer> getAllItemIdByMbrId(Integer mbrId) {
		List<Integer> itemIdList = new ArrayList<>();

		try (Jedis jedis = jedisPool.getResource()) {
			jedis.select(REDIS_NUMBER);
			Set<String> itemIdSet = jedis.zrevrange(MBR_PREFIX + mbrId + MBR_SUFFIX, 0, -1);
			if (itemIdSet != null) {
				for (String id : itemIdSet) {
					itemIdList.add(Integer.valueOf(id.replace(ITEM_PREFIX, "")));
				}
			}

			return itemIdList;
		}
	}
}
