<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="partslistApp">
<head>
    <title>Parts List</title>
    <link rel="stylesheet" href="/resources/css/mystyles.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="/resources/js/app.js"></script>
</head>
<body ng-controller="partsController">
<div>
    <p><input type="radio" name="filter" ng-model="required" value="" ng-click="filter(required)"/> Все</p>
    <p><input type="radio" name="filter" ng-model="required" value="yes" ng-click="filter(required)"/> Только обязательные</p>
    <p><input type="radio" name="filter" ng-model="required" value="no" ng-click="filter(required)"/> Только необязательные</p>
</div>
<div>
    <p><input type="text" placeholder="Поиск по наименованиям" ng-model="searchParts.name"/>
        <button ng-click="searchForName(searchParts)">Поиск</button>
    </p>
</div>
<div class="content">
    <table>
        <tr>
            <th>Наименование</th>
            <th>Необходимость</th>
            <th>Количество</th>
        </tr>
        <tr ng-repeat="part in partsList.parts" ng-include="getTemplate(part)">
        </tr>

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
            <td>Можно собрать</td>
            <td ng-bind="partsList.canAssemblyComps">{{partsList.canAssemblyComps}}</td>
            <td>компьютеров</td>
        </tr>
    </table>
    <div>
        <button ng-click="prevPage()">Назад</button>
        <button ng-click="nextPage()">Вперед</button>
    </div>
    <form name="addForm">
        <p>Добавить наименование</p>
        <p><input type="text" ng-model="newPart.name" required placeholder="Наименование"/></p>
        <p><input type="number" min="0" max="1000" ng-model="newPart.amount" required placeholder="Количество"/></p>
        <p><label>Необходимость</label><input type="checkbox" ng-model="newPart.required"/></p>
        <p>
            <button type="submit" ng-click="addPart(newPart, addForm)">Добавить</button>
        </p>
    </form>
</div>
</body>
</html>
