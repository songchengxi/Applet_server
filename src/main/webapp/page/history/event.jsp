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

                    $scope.data = {
                        dynasty:'<%=id%>'
                    };

                    $scope.rows = [];

                    //初始化事件
                    $http({
                        url: '${basePath}/history/getEvent.do?id=<%=id%>',
                        method: 'POST'
                    }).then(function (rows) {
                        for (var i in rows.data) {
                            var row = rows.data[i];
                            $scope.rows.push(row);
                        }
                    });

                    //添加
                    $scope.add = function () {
                        $scope.data = {
                            dynasty:'<%=id%>',
                            sort: '',
                            title: '',
                            content: ''
                        };
                    };

                    //编辑
                    $scope.edit = function (id) {
                        for (var i in $scope.rows) {
                            var row = $scope.rows[i];
                            if (id == row.id) {
                                $scope.data = row;
                                return;
                            }
                        }
                    };

                    //保存
                    $scope.save = function () {
                        var s = Number($scope.data.sort) + 2;
                        $http({
                            url: '${basePath}/history/saveEvent.do',
                            method: 'POST',
                            data: $scope.data
                        }).then(function (r) {
                            //保存成功后更新数据
                            $scope.responseBody = r.data;
                            $scope.get(r.data.result);
                            $scope.data = {
                                dynasty: '<%=id%>',
                                sort: s
                            };
                        });
                    };

                    //删除
                    $scope.del = function (id) {
                        $http({
                            url: '${basePath}/history/deleteEventById.do?id=' + id,
                            method: 'POST'
                        }).then(function (r) {
                            //删除成功后移除数据
                            $scope.remove(r.data.id);
                        });
                    };

                    //移除
                    $scope.remove = function (id) {
                        for (var i in $scope.rows) {
                            var row = $scope.rows[i];
                            if (id == row.id) {
                                $scope.rows.splice(i, 1);
                                return;
                            }
                        }
                    };

                    //获取数据
                    $scope.get = function (id) {
                        $http({
                            url: '${basePath}/history/getEventById.do?id=' + id,
                            method: 'POST'
                        }).then(function (data) {
                            data = data.data;
                            for (var i in $scope.rows) {
                                var row = $scope.rows[i];
                                if (data.id == row.id) {
                                    row.id = data.id;
                                    row.dynasty = data.dynasty;
                                    row.sort = data.sort;
                                    row.title = data.title;
                                    row.content = data.content;
                                    return;
                                }
                            }
                            $scope.rows.push(data);
                        });
                    };
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<h1>历史朝代</h1><%=id%>
<div>{{responseBody}}</div>
<br/>
<h3>事件：</h3>
<input type="button" value="添加" ng-click="add()"/>
<input type="button" value="保存" ng-click="save()"/>
<table cellspacing="1" style="background-color: #a0c6e5">
    <tr>
        <td>ID：</td>
        <td><input ng-model="data.id"/></td>
        <td>朝代：</td>
        <td><input ng-model="data.dynasty"/></td>
        <td>顺序：</td>
        <td><input ng-model="data.sort"/></td>
        <td>名称：</td>
        <td><input ng-model="data.title"/></td>
        <td>内容：</td>
        <td><textarea ng-model="data.content" cols="50" rows="5"></textarea></td>
    </tr>
</table>
<table cellspacing="1" style="background-color: #a0c6e5">
    <tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
        <td>操作</td>
        <td>ID</td>
        <td>朝代</td>
        <td>顺序</td>
        <td>名称</td>
        <td>内容</td>
    </tr>
    <tr ng-repeat="row in rows" bgcolor='#F4FAFF'>
        <td>
            <input ng-click="edit(row.id)" value="编辑" type="button"/>
            <input ng-click="del(row.id)" value="删除" type="button"/>
        </td>
        <td>{{row.id}}</td>
        <td>{{row.dynasty}}</td>
        <td>{{row.sort}}</td>
        <td>{{row.title}}</td>
        <td>{{row.content}}</td>
    </tr>
</table>
<br/>
</body>
</html>
