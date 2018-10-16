app.controller('searchController',function ($scope,searchService) {
    $scope.productList=[];
    $scope.findAll=function () {
       searchService.findAll().success(
           function (response) {
               $scope.productList=response;
           }
       )
    }
    $scope.entity={};
    $scope.searchByName=function () {
        searchService.findByName($scope.entity).success(function (response) {
            $scope.productList=response;
        });
    }
})