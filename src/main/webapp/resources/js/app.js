var partslistApp = angular.module("partslistApp", []);

partslistApp.controller("partsController", function ($scope, $http) {
    var baseUrl = "http://localhost:8080/parts";
    var currentPage = 1;
    var itemsPerPage = 10;
    var commonParams = function (page, size) {
        return "page=" + page + "&size=" + size;
    };
    $scope.selectedPart = {};
    $scope.partsList = {};
    $scope.filterParam = "";
    $scope.searchText = "";
    var paramsForPaginate = function () {
        return "required=" + $scope.filterParam + "&search=" +
        $scope.searchText + "&" + commonParams(currentPage, itemsPerPage);
    };

    updateContent();

    var numberOfPages = function(){
        $http.get(baseUrl + "?required=" + $scope.filterParam + "&search=" +
            $scope.searchText/*"?required=" + $scope.filterParam*/).then(function success(response) {
            allSize = response.data.parts.length;
        });
        return Math.ceil(allSize / itemsPerPage);
    };

    var allSize = numberOfPages();

    $scope.prevPage = function() {
        if (currentPage > 1)
            currentPage--;
        $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
            $scope.partsList = response.data;
        });
    };

    $scope.nextPage = function() {
        if (currentPage < numberOfPages())
            currentPage++;
        $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
            $scope.partsList = response.data;
        });
    };

    $scope.searchForName = function () {
        $http.get(baseUrl + "?" + paramsForPaginate()/*commonParams(currentPage, itemsPerPage) + "&search=" + $scope.searchText*/).then(function success(response) {
            $scope.partsList = response.data;
        })
    };

    $scope.clearSearchText = function () {
        $scope.searchText = "";
        updateContent();
    };

    $scope.filter = function (required) {
        $scope.filterParam = required;
        $http.get(baseUrl + "?" + paramsForPaginate()/*"?required=" + $scope.filterParam + "&" + commonParams(currentPage, itemsPerPage)*/).then(function success(response) {
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
        $http.put(baseUrl + "/" + part.id, $scope.selectedPart).then(function success() {
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
         $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
             $scope.partsList = response.data;
         });
     }
});