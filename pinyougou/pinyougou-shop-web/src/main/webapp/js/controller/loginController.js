app.controller("loginController", function (loginService, $scope) {
    $scope.getUsername = function () {
        loginService.getUsername().success(function (response) {
            $scope.username = response.username;
        })
    }
})