<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
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

                    $scope.data = {};
                    $scope.rows = [];

                    $scope.startDate='2017-05-16';
                    $scope.endDate='2017-06-16';

                    //初始化载入数据
                    $http({
                        url: '${basePath}/history/findAll.do',
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
                            id:'',
                            type: '',
                            info: '',
                            time: ''
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
                        $http({
                            url: '${basePath}/history/save.do',
                            method: 'POST',
                            data: $scope.data
                        }).then(function (r) {
                            //保存成功后更新数据
                            $scope.responseBody = r.data;
                            $scope.get(r.data.result);
                        });
                    };

                    //删除
                    $scope.del = function (id) {
                        $http({
                            url: '${basePath}/history/delete.do?id=' + id,
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
                            url: '${basePath}/history/get.do?id=' + id,
                            method: 'POST'
                        }).then(function (data) {
                            data = data.data;
                            for (var i in $scope.rows) {
                                var row = $scope.rows[i];
                                if (data.id == row.id) {
                                    row.id = data.id;
                                    row.type = data.type;
                                    row.info = data.info;
                                    row.time = data.time;
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
<h1>历史朝代</h1>
<%--<input type="button" value="添加" ng-click="add()"/>--%>
<%--<input type="button" value="保存" ng-click="save()"/>--%>

<br/>
<br/>
<h3>历史朝代信息：</h3>
<input type="hidden" ng-model="data.id"/>
<table cellspacing="1" style="background-color: #a0c6e5">
    <tr>
        <td>ID：</td>
        <td><input ng-model="data.id"/></td>
        <td>朝代：</td>
        <td><input ng-model="data.name"/></td>
        <td>时间：</td>
        <td><input ng-model="data.time"/></td>
        <td>都城：</td>
        <td><input ng-model="data.capital"/></td>
        <td>都城现址：</td>
        <td><input ng-model="data.capitalNow"/></td>
        <td>建立者：</td>
        <td><input ng-model="data.founder"/></td>
        <td>简介：</td>
        <td><input ng-model="data.introduction"/></td>
    </tr>
</table>
<div>{{responseBody}}</div>
<br/>
<h3>朝代信息：</h3>
<table cellspacing="1" style="background-color: #a0c6e5">
    <tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
        <td>操作</td>
        <td>ID</td>
        <td>朝代</td>
        <td>时间</td>
        <td>都城</td>
        <td>都城现址</td>
        <td>建立者</td>
        <td>简介</td>
    </tr>
    <tr ng-repeat="row in rows" bgcolor='#F4FAFF'>
        <td>
            <%--<input ng-click="edit(row.id)" value="编辑" type="button"/>--%>
            <%--<input ng-click="del(row.id)" value="删除" type="button"/>--%>
        </td>
        <td>{{row.id}}</td>
        <td>{{row.name}}</td>
        <td>{{row.time}}</td>
        <td>{{row.capital}}</td>
        <td>{{row.capitalNow}}</td>
        <td>{{row.founder}}</td>
        <td>{{row.introduction}}</td>
    </tr>
</table>
<br/>

</body>
</html>
