	<%@ page language="java" contentType="text/html; charset=BIG5"
	    pageEncoding="BIG5"%>
	<!DOCTYPE html>
	<html lang="en">
	
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Change Password</title>
	    <style>
	        /* ���F�t�ܡA�N�˦�������bHTML�� */
	        #passwordPopup {
	            display: none;
	            position: fixed;
	            top: 50%;
	            left: 50%;
	            transform: translate(-50%, -50%);
	            padding: 20px;
	            background-color: #fff;
	            border: 1px solid #ccc;
	            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	            z-index: 1000;
	            text-align: center; /* �s�W�o�@�� */
	        }
	
	        /* �s�W���˦� */
	        #passwordPopup input,
	        #passwordPopup button {
	            margin: 10px 0; /* �վ㶡�Z */
	        }
	    </style>
	</head>
	
	<body>
	
	    <!-- �I�����K�X���s��Ĳ�o���ƥ� -->
	    
	    <button id="pswdHashFormA">���K�X</button>
	
	    <!-- �u�X�����f -->
	    <div id="passwordPopup">
	        <form method="post" id="pswdHashForm" class="UpdatePswdHash" >
	            <input type="password" id="pswdHash" name="pswdHash" placeholder="�K�X">
	            <input type="hidden" id="mbrId" name="mbrId" value="1">
	            <span id="pswdHashError" style="color: red;"></span>
	            <input type="hidden" name="action" value="members_UpdatePswdHash_1">
	            <input type="submit" value="�T�w">
	        </form>
	        <button id="pswdHashFormCancel">����</button>
	    </div>
	
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script>

    	    
    	    
	        $(document).ready(function () {
	            // �I�����K�X���s��Ĳ�o���ƥ�
	            $('#pswdHashFormA').click(function () {
	                // ��ܼu�X���f
	                $('#passwordPopup').show();
	            });
	
	            // �I���������s��Ĳ�o���ƥ�
	            $('#pswdHashFormCancel').click(function () {
	                // ���üu�X���f
	                $('#passwordPopup').hide();
	                // �M�ſ�J���K�X
	                $('#pswdHash').val('');
	                // �M�ſ��~����
	                $('#pswdHashError').text('');
	            });
		
	        	
	        	    
	            // �������Ĳ�o���ƥ�
	            $('#pswdHashForm').submit(function (event) {
	                event.preventDefault();
	                // ������ƾ�
	                var formData = $(this).serialize();
	
	                // �o�eAjax�ШD
	                $.ajax({
	                    type: 'POST',
	                    url: '${pageContext.request.contextPath}/members/Members.do',
	                    data: formData,
	                    dataType: 'json',
	                    success: function (response) {
	                        if (response.success) {
	                            if (response.location !== null && response.location !== undefined) {
	                                window.location.href = response.location;
	                            } else {
	                                window.location.href = '${pageContext.request.contextPath}/front_end/members/UpdatePswdHash.jsp';
	                            }
	                        } else {
	                            $('#pswdHashError').text(response.errors.error);
	                        }
	                    },
	                    error: function (jqXHR, textStatus, errorThrown) {
	                        alert('AJAX ���~�G' + errorThrown);
	                        console.error('AJAX ���~:', textStatus, errorThrown);
	                        console.log('�T��:', jqXHR.responseText);
	                    }
	                });
	            });
	        });
	    </script>
	
	</body>
	
	</html>
