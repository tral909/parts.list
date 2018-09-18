var partslistApp = angular.module("partslistApp", []);

partslistApp.controller("partsController", function ($scope, $http) {
    var baseUrl = "http://localhost:8080/parts";

    updateContent();

    $scope.selectedPart = {};
    $scope.partsList = {};

    $scope.searchForName = function (searchParts) {
        $http.get(baseUrl + "?search=" + searchParts.name).then(function success(response) {
            $scope.partsList = response.data;
        })
    };

    $scope.filter = function (required) {
        $http.get(baseUrl + "?required=" + required).then(function success(response) {
            $scope.partsList = response.data;
        })
    };

    //edit part
    $scope.getTemplate = function (part) {
        if (part.id === $scope.selectedPart.id) {
            return "edit";
        } else {
            return "display";
        }
    };

    $scope.editPart = function (part) {
        $scope.selectedPart = angular.copy(part);
    };

    $scope.savePart = function (part) {
        $http.put(baseUrl + "/" + part.id, $scope.selectedPart).then(function success(response) {
            //$scope.partsList.parts[--part.id] = response.data; //для мокДао, потом уберу ид из листа нафиг
            updateContent();
        });
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedPart = {};
    };
    //end edit part

    $scope.addPart = function (newPart, addForm) {
        if (addForm.$valid) {
            $http.post(baseUrl, newPart).then(function success() {
                updateContent();
            })
        }
    };

    $scope.deletePart = function (part) {
        $http.delete(baseUrl + "/" + part.id).then(function success() {
            updateContent();
        })
    };

     function updateContent() {
         $http.get(baseUrl).then(function success(response) {
             $scope.partsList = response.data;
        });
     }
});