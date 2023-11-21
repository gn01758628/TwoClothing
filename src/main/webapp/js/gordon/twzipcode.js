/**
 * TWzipcode v3.0 (https://code.essoduke.org/twzipcode)
 * @license MIT
 */
 !function(t){"use strict";"undefined"!=typeof module&&void 0!==module.exports?module.exports=t():window.TWzipcode=t()}((function(){let t={"基隆市":{"仁愛區":"200","信義區":"201","中正區":"202","中山區":"203","安樂區":"204","暖暖區":"205","七堵區":"206"},"臺北市":{"中正區":"100","大同區":"103","中山區":"104","松山區":"105","大安區":"106","萬華區":"108","信義區":"110","士林區":"111","北投區":"112","內湖區":"114","南港區":"115","文山區":"116"},"新北市":{"萬里區":"207","金山區":"208","板橋區":"220","汐止區":"221","深坑區":"222","石碇區":"223","瑞芳區":"224","平溪區":"226","雙溪區":"227","貢寮區":"228","新店區":"231","坪林區":"232","烏來區":"233","永和區":"234","中和區":"235","土城區":"236","三峽區":"237","樹林區":"238","鶯歌區":"239","三重區":"241","新莊區":"242","泰山區":"243","林口區":"244","蘆洲區":"247","五股區":"248","八里區":"249","淡水區":"251","三芝區":"252","石門區":"253"},"宜蘭縣":{"宜蘭市":"260","頭城鎮":"261","礁溪鄉":"262","壯圍鄉":"263","員山鄉":"264","羅東鎮":"265","三星鄉":"266","大同鄉":"267","五結鄉":"268","冬山鄉":"269","蘇澳鎮":"270","南澳鄉":"272"},"新竹市":{"東區":"300","北區":"300","香山區":"300"},"新竹縣":{"竹北市":"302","湖口鄉":"303","新豐鄉":"304","新埔鎮":"305","關西鎮":"306","芎林鄉":"307","寶山鄉":"308","竹東鎮":"310","五峰鄉":"311","橫山鄉":"312","尖石鄉":"313","北埔鄉":"314","峨眉鄉":"315"},"桃園市":{"中壢區":"320","平鎮區":"324","龍潭區":"325","楊梅區":"326","新屋區":"327","觀音區":"328","桃園區":"330","龜山區":"333","八德區":"334","大溪區":"335","復興區":"336","大園區":"337","蘆竹區":"338"},"苗栗縣":{"竹南鎮":"350","頭份市":"351","三灣鄉":"352","南庄鄉":"353","獅潭鄉":"354","後龍鎮":"356","通霄鎮":"357","苑裡鎮":"358","苗栗市":"360","造橋鄉":"361","頭屋鄉":"362","公館鄉":"363","大湖鄉":"364","泰安鄉":"365","銅鑼鄉":"366","三義鄉":"367","西湖鄉":"368","卓蘭鎮":"369"},"臺中市":{"中區":"400","東區":"401","南區":"402","西區":"403","北區":"404","北屯區":"406","西屯區":"407","南屯區":"408","太平區":"411","大里區":"412","霧峰區":"413","烏日區":"414","豐原區":"420","后里區":"421","石岡區":"422","東勢區":"423","和平區":"424","新社區":"426","潭子區":"427","大雅區":"428","神岡區":"429","大肚區":"432","沙鹿區":"433","龍井區":"434","梧棲區":"435","清水區":"436","大甲區":"437","外埔區":"438","大安區":"439"},"彰化縣":{"彰化市":"500","芬園鄉":"502","花壇鄉":"503","秀水鄉":"504","鹿港鎮":"505","福興鄉":"506","線西鄉":"507","和美鎮":"508","伸港鄉":"509","員林市":"510","社頭鄉":"511","永靖鄉":"512","埔心鄉":"513","溪湖鎮":"514","大村鄉":"515","埔鹽鄉":"516","田中鎮":"520","北斗鎮":"521","田尾鄉":"522","埤頭鄉":"523","溪州鄉":"524","竹塘鄉":"525","二林鎮":"526","大城鄉":"527","芳苑鄉":"528","二水鄉":"530"},"南投縣":{"南投市":"540","中寮鄉":"541","草屯鎮":"542","國姓鄉":"544","埔里鎮":"545","仁愛鄉":"546","名間鄉":"551","集集鎮":"552","水里鄉":"553","魚池鄉":"555","信義鄉":"556","竹山鎮":"557","鹿谷鄉":"558"},"嘉義市":{"東區":"600","西區":"600"},"嘉義縣":{"番路鄉":"602","梅山鄉":"603","竹崎鄉":"604","阿里山鄉":"605","中埔鄉":"606","大埔鄉":"607","水上鄉":"608","鹿草鄉":"611","太保市":"612","朴子市":"613","東石鄉":"614","六腳鄉":"615","新港鄉":"616","民雄鄉":"621","大林鎮":"622","溪口鄉":"623","義竹鄉":"624","布袋鎮":"625"},"雲林縣":{"斗南鎮":"630","大埤鄉":"631","虎尾鎮":"632","土庫鎮":"633","褒忠鄉":"634","東勢鄉":"635","臺西鄉":"636","崙背鄉":"637","麥寮鄉":"638","斗六市":"640","林內鄉":"643","古坑鄉":"646","莿桐鄉":"647","西螺鎮":"648","二崙鄉":"649","北港鎮":"651","水林鄉":"652","口湖鄉":"653","四湖鄉":"654","元長鄉":"655"},"臺南市":{"中西區":"700","東區":"701","南區":"702","北區":"704","安平區":"708","安南區":"709","永康區":"710","歸仁區":"711","新化區":"712","左鎮區":"713","玉井區":"714","楠西區":"715","南化區":"716","仁德區":"717","關廟區":"718","龍崎區":"719","官田區":"720","麻豆區":"721","佳里區":"722","西港區":"723","七股區":"724","將軍區":"725","學甲區":"726","北門區":"727","新營區":"730","後壁區":"731","白河區":"732","東山區":"733","六甲區":"734","下營區":"735","柳營區":"736","鹽水區":"737","善化區":"741","大內區":"742","山上區":"743","新市區":"744","安定區":"745"},"高雄市":{"新興區":"800","前金區":"801","苓雅區":"802","鹽埕區":"803","鼓山區":"804","旗津區":"805","前鎮區":"806","三民區":"807","楠梓區":"811","小港區":"812","左營區":"813","仁武區":"814","大社區":"815","岡山區":"820","路竹區":"821","阿蓮區":"822","田寮區":"823","燕巢區":"824","橋頭區":"825","梓官區":"826","彌陀區":"827","永安區":"828","湖內區":"829","鳳山區":"830","大寮區":"831","林園區":"832","鳥松區":"833","大樹區":"840","旗山區":"842","美濃區":"843","六龜區":"844","內門區":"845","杉林區":"846","甲仙區":"847","桃源區":"848","那瑪夏區":"849","茂林區":"851","茄萣區":"852"},"屏東縣":{"屏東市":"900","三地門鄉":"901","霧臺鄉":"902","瑪家鄉":"903","九如鄉":"904","里港鄉":"905","高樹鄉":"906","鹽埔鄉":"907","長治鄉":"908","麟洛鄉":"909","竹田鄉":"911","內埔鄉":"912","萬丹鄉":"913","潮州鎮":"920","泰武鄉":"921","來義鄉":"922","萬巒鄉":"923","崁頂鄉":"924","新埤鄉":"925","南州鄉":"926","林邊鄉":"927","東港鎮":"928","琉球鄉":"929","佳冬鄉":"931","新園鄉":"932","枋寮鄉":"940","枋山鄉":"941","春日鄉":"942","獅子鄉":"943","車城鄉":"944","牡丹鄉":"945","恆春鎮":"946","滿州鄉":"947"},"臺東縣":{"臺東市":"950","綠島鄉":"951","蘭嶼鄉":"952","延平鄉":"953","卑南鄉":"954","鹿野鄉":"955","關山鎮":"956","海端鄉":"957","池上鄉":"958","東河鄉":"959","成功鎮":"961","長濱鄉":"962","太麻里鄉":"963","金峰鄉":"964","大武鄉":"965","達仁鄉":"966"},"花蓮縣":{"花蓮市":"970","新城鄉":"971","秀林鄉":"972","吉安鄉":"973","壽豐鄉":"974","鳳林鎮":"975","光復鄉":"976","豐濱鄉":"977","瑞穗鄉":"978","萬榮鄉":"979","玉里鎮":"981","卓溪鄉":"982","富里鄉":"983"},"金門縣":{"金沙鎮":"890","金湖鎮":"891","金寧鄉":"892","金城鎮":"893","烈嶼鄉":"894","烏坵鄉":"896"},"連江縣":{"南竿鄉":"209","北竿鄉":"210","莒光鄉":"211","東引鄉":"212"},"澎湖縣":{"馬公市":"880","西嶼鄉":"881","望安鄉":"882","七美鄉":"883","白沙鄉":"884","湖西鄉":"885"}};const e=(t,e,s)=>{let n;-1!==e.indexOf(".")?window.CustomEvent&&(n=new CustomEvent(e)):(n=new Event(e,{bubbles:!0,cancelable:!0}),document.dispatchEvent(n)),t.dispatchEvent(n),i.isFunction(s)&&s.call()};class i{static deepExtend(...t){let e={},i=!1,s=t.length,n=0;"boolean"==typeof t[0]&&(i=t[0],n+=1);const o=t=>{for(let s in t)Object.prototype.hasOwnProperty.call(t,s)&&(e[s]=i&&this.isObject(t[s])?this.deepExtend(!0,e[s],t[s]):t[s])};for(;n<s;n+=1){o(t[n])}return e}static findObject(t,e){if(e&&this.isObject(t)){let i=t;if(this.isString(e)){let t=e.split(/\./g);for(let e of t)i.hasOwnProperty(e)&&(i=i[e]);return i}}}static transfer(t){return this.isString(t)?t.replace(/[台]+/gi,"臺"):t}static isset(...t){let e=!0;for(let i of t)e=e&&(void 0!==i||"[object Undefined]"!==Object.prototype.toString.call(i))&&null!==i;return e}static isDOM(...t){let e=!0;for(let i of t)e=e&&i instanceof HTMLElement;return e}static isString(...t){let e=!0;for(let i of t)e=e&&("string"==typeof i||"[object String]"===Object.prototype.toString.call(i));return e}static isNumeric(...t){let e=!0;for(let i of t)e=e&&/^[0-9]+$/.test(i);return e}static isBool(...t){let e=!0;for(let i of t)e=e&&"boolean"==typeof i||"[object Boolean]"===Object.prototype.toString.call(i);return e}static isArray(...t){let e=!0;for(let i of t)e=e&&(i instanceof Array||Array.isArray(i)||"[object Array]"===Object.prototype.toString.call(i));return e}static isObject(...t){let e=!0;for(let i of t)e=e&&"object"==typeof i&&"[object Object]"===Object.prototype.toString.call(i)&&null!==i;return e}static isFunction(...t){let e=!0;for(let i of t)e=e&&("function"==typeof i||"[object Function]"===Object.prototype.toString.call(i));return e}}return class{version="3.0";database;options;container;selector;instance={};fetch;constructor(e=".twzipcode",s={}){if(this.options=i.deepExtend(!0,{county:{tag:"select",name:"county",css:"",label:"縣市",value:"",hidden:!1,onChange:null},district:{tag:"select",name:"district",css:"",label:"鄉鎮市區",value:"",hidden:!1,onChange:null},zipcode:{tag:"input",type:"hidden",name:"zipcode",css:"",value:"",onKeyup:null},address:{tag:"input",type:"text",name:"address",css:"",value:"",onKeyup:null},combine:!0,islands_key:["綠島鄉","蘭嶼鄉","金門縣","連江縣","澎湖縣","琉球鄉"],islands_hidden:!1},s),this.database=t,i.isString(e))this.container=document.querySelectorAll(e);else if(i.isDOM(e))this.container=[e];else if(e instanceof NodeList||Object.prototype.isPrototypeOf.call(NodeList.prototype,e))this.container=e;else{if(!i.isObject(e))throw"錯誤：無法初始化 Selector 元素。";this.container=document.querySelectorAll(".twzipcode"),this.options=i.deepExtend(!0,this.options,e)}return this.init(),this.bindEvents(),this.trigger(),this}init(){const t=this.options.islands_key,e=Boolean(this.options.islands_hidden),s=this.options.county.hidden;return this.container.forEach((n=>{let{c:o,d:a,z:r,a:c}=this.createEmelents(n),l=Math.random().toString(36).substring(2,10),d=[];if(n.dataset.id=l,i.isDOM(o,a)){i.isDOM(c)&&(c.dataset.id=`address-${l}`);let u=`<option value="">${"label"in a.dataset?a.dataset.label:this.options.district.label}</option>`;this.instance[l]={parent:n,county:o,district:a,zipcode:r,address:c};let h="label"in o.dataset?o.dataset.label:this.options.county.label;const p=o.dataset.hasOwnProperty("loaded")&&Boolean(o.dataset.loaded);d.push(`<option value="">${h}</option>`),Object.keys(this.database).forEach((n=>{const o=e&&t.includes(n),a=(i.isString(s)||i.isArray(s))&&s.includes(n);o||a||d.push(`<option value="${n}">${n}</option>`)})),o.dataset.id=`county-${l}`,p||(o.innerHTML=d.join(""),o.dataset.loaded=!0,a.dataset.id=`district-${l}`,a.innerHTML=u)}})),this}createEmelents(t){const e=()=>({c:t.querySelector('[data-role="county"]'),d:t.querySelector('[data-role="district"]'),z:t.querySelector('[data-role="zipcode"]'),a:t.querySelector('[data-role="address"]')});let s=e();if(i.isDOM(s.c,s.d,s.z))return s;for(let e in this.options){let i=this.options[e];if(!1!==i){let s=document.createElement(i.tag);if(s.dataset.role=e,s.setAttribute("name",i.name),s.setAttribute("class",i.css),"input"===i.tag)s.setAttribute("type",this.options.combine?"hidden":"text");else s.setAttribute("label",this.options.label);t.appendChild(s)}}return e()}bindEvents(){const t=this,s=Boolean(this.options.combine),n=this.options.islands_key,o=Boolean(this.options.islands_hidden),a=this.options.district.label,r=this.options.district.hidden,c=this.options.county.onChange,l=this.options.district.onChange,d=this.database,u=function(l){const u=l.target,h=i.findObject(d,u.value);let p,y=u.dataset.id.match(/.*\-(.*)/),f=[];if(i.isset(y[1])&&(p=y[1]),u.dataset.listener=!0,h){for(let t in h){const e=o&&n.includes(t),a=(i.isString(r)||i.isArray(r))&&r.includes(t);if(!e&&!a){let e=s?`${h[t]} ${t}`:t;f.push(`<option value="${t}">${e}</option>`)}}this.district.innerHTML=f.join("")}else{let t="label"in this.district.dataset?this.district.dataset.label:a;this.district.innerHTML=`<option value="">${t}</option>`}i.isFunction(c)&&c.call(t,p,u,l),e(this.district,"change")},h=function(e){const s=e.target;let n,o=s.dataset.id.match(/.*\-(.*)/);if(i.isset(o[1])&&(n=o[1]),s.dataset.listener=!0,d.hasOwnProperty(this.county.value)&&d[this.county.value].hasOwnProperty(s.value)){const o=d[this.county.value][s.value];i.isDOM(this.zipcode)&&(this.zipcode.value=o.toString()),i.isFunction(l)&&l.call(t,n,s,e)}else this.zipcode&&(this.zipcode.value=""),s.innerHTML=`<option value="">${a}</option>`},p=function(t){const s=t.target;let n;s.dataset.listener=!0;t:for(let t in d)for(let e in d[t])if(d[t].hasOwnProperty(e)&&s.value==d[t][e]){n={county:t,district:e,zipcode:s.value};break t}if(i.isObject(n)&&"county"in n&&"district"in n&&"zipcode"in n){const t=function(t=10){return new Promise(((e,i)=>{window.setTimeout(e,t)}))};t().then((()=>(this.county.value=n.county,e(this.county,"change"),t()))),t().then((()=>(this.district.value=n.district,e(this.district,"change"),t())))}return n},y=t=>{let e=i.transfer(t.target.value),s=this.parseAddress(e);i.isObject(s)&&(e=e.replace(s.zipcode,"").replace(s.county,"").replace(s.district,"")),t.target.value=e.trim()};Object.values(this.instance).forEach((t=>{if(i.isDOM(t.county,t.district)){const e=t.county.dataset.hasOwnProperty("listener")&&Boolean(t.county.dataset.listener),i=t.district.dataset.hasOwnProperty("listener")&&Boolean(t.district.dataset.listener);e||t.county.addEventListener("change",u.bind(t)),i||t.district.addEventListener("change",h.bind(t))}if(i.isDOM(t.zipcode)){t.zipcode.dataset.hasOwnProperty("listener")&&Boolean(t.zipcode.dataset.listener)||t.zipcode.addEventListener("keyup",p.bind(t))}if(i.isDOM(t.address)){t.address.dataset.hasOwnProperty("listener")&&Boolean(t.address.dataset.listener)||t.address.addEventListener("blur",y)}}))}trigger(){for(let t of Object.values(this.instance)){if(i.isDOM(t.zipcode)&&"value"in t.zipcode.dataset){this.zipcode(t.zipcode.dataset.value);break}i.isDOM(t.county,t.district)&&("value"in t.county.dataset&&this.county(t.county.dataset.value),"value"in t.district.dataset&&this.district(t.district.dataset.value))}}nth(t){const e=this.instance;let s;if(i.isNumeric(t)){let i=Object.values(e).filter(((e,i)=>i===t));i.length&&(s=[i[0]])}else s=i.isString(t)&&e.hasOwnProperty(t)?[e[t]]:e;return this.fetch=s,this}get(t){const e=i.isset(this.fetch)?this.fetch:this.instance,s=Object.keys(e).length;let n=["county","district","zipcode"],o=[];return i.isArray(t)?n=t:i.isString(t)&&n.includes(t)&&(n=[t]),Object.values(e).forEach((t=>{let e={};for(let s of n)t.hasOwnProperty(s)&&i.isset(t[s])&&(e[s]=t[s].value);o.push(e)})),1===s?i.isString(t)&&t in o[0]?o[0][t]:o[0]:o}set(t){const s=i.isset(this.fetch)?this.fetch:this.instance;let n={county:null,district:null,zipcode:null};const o=function(t=10){return new Promise(((e,i)=>{window.setTimeout(e,t)}))};return i.isObject(t)?n=t:i.isNumeric(t)&&(n.zipcode=t),Object.values(s).forEach((t=>{"county"in n&&i.isString(n.county)&&o().then((()=>(t.county.value=i.transfer(n.county),e(t.county,"change"),o()))),"district"in n&&i.isString(n.district)&&o().then((()=>(t.district.value=i.transfer(n.district),e(t.district,"change"),o()))),"zipcode"in n&&i.isNumeric(n.zipcode)&&o().then((()=>(t.zipcode.value=n.zipcode,e(t.zipcode,"keyup"),o())))})),this}county(t){return i.isString(t)?(this.set({county:t}),this):this.get("county")}district(t){return i.isString(t)?(this.set({district:t}),this):this.get("district")}zipcode(t){return i.isString(t)?(this.set({zipcode:t}),this):this.get("zipcode")}isIslands(t){const e=i.isset(this.fetch)?this.fetch:this.instance,s=Object.keys(e).length;let n=i.isString(t)||i.isArray(t)?t:this.options.islands_key,o={};if(Object.keys(e).forEach((t=>{const e=this.instance[t];let i=!1;e.county.value.length&&(i=i||n.includes(e.county.value)),e.district.value.length&&(i=i||n.includes(e.district.value)),e.zipcode.value.length&&(i=i||n.includes(e.zipcode.value)),o[t]=i})),1===s){const t=Object.values(o).filter(((t,e)=>0===e));if(t.length)return t[0]}return o}parseAddress(t){t=i.transfer(t);const e=/(?<zipcode>\d+)?(?<county>\D+[縣市])(?<district>\D+?(市區|鎮區|鎮市|[鄉鎮市區]))(?<village>\D+?[村里])?(?<neighbor>\d+[鄰])?(?<road>\D+?(村路|[路街道段]))?(?<section>.+?段)?(?<lane>.+?巷)?(?<alley>\d+弄)?(?<num>.+號)?(?<others>.+)?/gi.exec(t);if(i.isset(e)&&i.isset(e.groups))return e.groups}destroy(){i.isset(this.fetch)?Object.keys(this.fetch).forEach((t=>{const e=this.fetch[t];if(e.hasOwnProperty("parent")&&"id"in e.parent.dataset){const s=e.parent.dataset.id;i.isset(this.instance[s])&&(e.parent.innerHTML="",this.fetch.splice(t,1))}})):Object.keys(this.instance).forEach((t=>{const e=this.instance[t];e.hasOwnProperty("parent")&&"id"in e.parent.dataset&&(e.parent.innerHTML="")}))}serialize(){const t=[];return Object.values(this.instance).forEach((e=>{const i={};e.hasOwnProperty("county")&&(i[e.county.name]=e.county.value),e.hasOwnProperty("district")&&(i[e.district.name]=e.district.value),e.hasOwnProperty("zipcode")&&(i[e.zipcode.name]=e.zipcode.value),t.push(new URLSearchParams(i).toString())})),t.join("&")}}}));

