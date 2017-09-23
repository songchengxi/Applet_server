<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<%
    String id = request.getParameter("id");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>历史朝代</title>
    <%@ include file="/includes/headinclude.jsp" %>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);
        app.controller(
                'MainController',
                function ($rootScope, $scope, $http) {

                    //初始化详细信息
                    $http({
                        url: '${basePath}/history/getDetail.do?id=<%=id%>',
                        method: 'POST'
                    }).then(function (rows) {
                        $scope.responseBody = rows;
                        $scope.data = rows.data;
                    });


                    $scope.data = {
                        id:'<%=id%>'
                    };

                    //保存
                    $scope.save = function () {
                        $http({
                            url: '${basePath}/history/saveDetail.do',
                            method: 'POST',
                            data: $scope.data
                        }).then(function (r) {
                            //保存成功后更新数据
                            $scope.responseBody = r.data;
                        });
                    };
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<h1>历史朝代</h1><%=id%>
<input type="text" ng-model="data.id"/>

<h3>简介：</h3>
<textarea ng-model="data.profile" cols="70" rows="10"></textarea>

<h3>疆域：</h3>
<textarea ng-model="data.territory" cols="70" rows="10"></textarea>
<%--<input type="button" value="保存" ng-click="save()"/>--%>
<br/>
<br/>
<div>{{responseBody}}</div>
<br/>

</body>
</html>
