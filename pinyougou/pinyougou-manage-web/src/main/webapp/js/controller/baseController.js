app.controller("baseController", function ($scope) {
    $scope.paginationConf = {
        currentPage: 1,// 当前页号
        totalItems: 10,// 总记录数
        itemsPerPage: 10,// 页大小
        perPageOptions: [10, 20, 30, 40, 50],// 可选择的每页大小
        onChange: function () {// 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    }
    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    //是否选中，若选中，添加到id集合
    $scope.selectedIds = [];

    //一个临时用来存储从后台显示当前每页所有的id集合
    $scope.temporarySelectedIds = [];
    $scope.updateSelection = function (event, id) {
        if (event.target.checked) {
            $scope.selectedIds.push(id);
        } else {
            var index = $scope.selectedIds.indexOf(id);
            $scope.selectedIds.splice(index, 1);
        }
    };

    //json转string
    $scope.jsonToString = function (jsonArray, key) {
        var jsonA = JSON.parse(jsonArray);
        var str = "";
        for (var i = 0; i < jsonA.length; i++) {
            var jsobj = jsonA[i];
            str += jsobj[key] + ",";
        }
        str = str.substring(0, str.length - 1);
        return str;
    }
})