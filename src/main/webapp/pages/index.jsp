<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="partslistApp">
<head>
    <title>Parts List</title>
    <link rel="stylesheet" href="/resources/css/mystyles.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="/resources/js/app.js"></script>
</head>
<body ng-controller="partsController">
<div class="container-fluid">
    <div class="row">
        <div class="col left">
            <div class="filters">
                <p><label><input type="radio" name="filter" ng-model="required" value="" ng-click="filter(required)"/>Все</label></p>
                <p><label><input type="radio" name="filter" ng-model="required" value="yes" ng-click="filter(required)"/>Только обязательные</label></p>
                <p><label><input type="radio" name="filter" ng-model="required" value="no" ng-click="filter(required)"/>Только необязательные</label></p>
            </div>
        </div>
        <div class="col-6 center">
            <h1>Parts.list application</h1>
            <div class="search">
                <p><input type="text" placeholder="Поиск" ng-model="searchText"/>
                    <button ng-click="searchForName()">Поиск</button>
                    <button ng-click="clearSearchText()">Отменить</button>
                </p>
            </div>
            <div class="content">
                <table class="table table-striped table-border">
                    <thead>
                    <tr>
                        <th>Наименование</th>
                        <th>Необходимость</th>
                        <th>Количество</th>
                    </tr>
                    </thead>
                    <tbody class="table-bordered">
                    <tr ng-repeat="part in partsList.parts" ng-include="getTemplate(part)">
                    </tr>
                    </tbody>

                    <script type="text/ng-template" id="display">
                        <td>{{part.name}}</td>
                        <td>{{part.required}}</td>
                        <td>{{part.amount}}</td>
                        <td>
                            <button ng-click="editPart(part)">Изменить</button>
                        </td>
                        <td>
                            <button ng-click="deletePart(part)">Удалить</button>
                        </td>
                    </script>

                    <script type="text/ng-template" id="edit">
                        <td><input type="text" ng-model="selectedPart.name" required/></td>
                        <td><input type="checkbox" ng-model="selectedPart.required" required/></td>
                        <td><input type="number" min="0" max="1000" required ng-model="selectedPart.amount"/></td>
                        <td>
                            <button ng-click="savePart(part)">Сохранить</button>
                        </td>
                        <td>
                            <button ng-click="reset()">Отменить</button>
                        </td>
                    </script>

                    <tr>
                        <td colspan="2">Можно собрать</td>
                        <td ng-bind="partsList.canAssemblyComps">{{partsList.canAssemblyComps}}</td>
                        <td colspan="2">компьютеров</td>
                    </tr>
                </table>
                <div>
                    <button ng-click="prevPage()">Назад</button>
                    <button ng-click="nextPage()">Вперед</button>
                </div>
            </div>
        </div>
        <div class="col right">
            <form class="addForm">
                <p>Добавить наименование</p>
                <p><input type="text" ng-model="newPart.name" required placeholder="Наименование"/></p>
                <p><input type="number" min="0" max="1000" ng-model="newPart.amount" required placeholder="Количество"/>
                </p>
                <p><label>Необходимость<input type="checkbox" ng-model="newPart.required"/></label></p>
                <p>
                    <button type="submit" ng-click="addPart(newPart, addForm)">Добавить</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
