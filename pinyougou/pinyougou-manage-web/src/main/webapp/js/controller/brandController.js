app.controller("brandController", function ($scope, brandService, $controller) {
    //加载baseController控制器并传入1个作用域，与angularJs运行时作用域相同.
    $controller("baseController", {$scope: $scope});
    $scope.findPage = function (pageNo, rows) {
        brandService.findPage(pageNo, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    };

    //根据id查询
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity = response;
        });
    }

    //保存
    $scope.save = function () {
        var obj = null;
        if ($scope.entity.id != null) {
            obj = brandService.update($scope.entity);
        } else {
            obj = brandService.add($scope.entity);
        }
        obj.success(function (response) {
            if (response.success) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            } else {
                alert(response.message);
            }
        });
    };

    //删除
    $scope.delete = function () {
        if ($scope.selectedIds == null) {
            alert("请先选中要删除的品牌项");
            return;
        }
        if (confirm("您确定要删除选中的所有品牌项吗?")) {
            brandService.delete($scope.selectedIds).success(function (response) {
                if (response.success) {
                    $scope.selectedIds = [];
                    $scope.reloadList();
                    $scope.paginationConf.totalItems = response.total;
                } else {
                    alert(response.message);
                }
                ;
            });
        }
    };
    //修改
    $scope.update = function () {
        brandService.update($scope.entity).success(function (response) {
            if (response.success) {
                $scope.reloadList();
                $scope.paginationConf.totalItems = response.total;
            } else {
                alert(response.message);
            }
            ;
        });
    };

    $scope.searchEntity = {};
    //根据条件查询
    $scope.search = function (pageNo, rows) {
        brandService.search($scope.searchEntity, pageNo, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
});