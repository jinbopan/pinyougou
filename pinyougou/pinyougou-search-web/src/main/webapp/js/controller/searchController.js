app.controller("searchController", function ($scope, searchService, $location) {

    $scope.search = function () {
        searchService.searchService($scope.searchMap).success(function (response) {
            $scope.list = response;
            showPageList();
        })
    }

    $scope.searchMap = {
        "category": "", "brand": "",
        "price": "", "spec": {}, "sortBy": "", "sortField": "", "pageNo": 1, "pageSize": 20
    };
    //添加过滤条件
    $scope.addSearchItem = function (key, value) {
        if (key == "brand" || key == "category" || key == "price") {
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.searchMap.pageNo=1;
        $scope.search();
    }
    //移除过滤条件
    $scope.removeSearchItem = function (key) {
        if (key == "brand" || key == "category" || key == "price") {
            $scope.searchMap[key] = "";
        } else {
            delete $scope.searchMap.spec[key];
        }
        $scope.search();
    }
    //排序
    $scope.addFilter = function (key, value) {
        $scope.searchMap.sortField = key;
        $scope.searchMap.sortBy = value;
        $scope.search();
    }

    //分页
    showPageList = function () {
        $scope.pageNoList = [];
        var startPage = 1;
        var endPage = $scope.list.totalPages;
        var showTotalPage = 5;    //页面总共要显示的5页
        //间隔页
        var jiangePage = Math.floor(showTotalPage / 2);
        if ($scope.list.totalPages > showTotalPage) {
            startPage = parseInt($scope.searchMap.pageNo) - jiangePage;
            endPage = parseInt($scope.searchMap.pageNo) + jiangePage;
            if (startPage > 0) {
                if (endPage > $scope.list.totalPages) {
                    startPage = $scope.list.totalPages - endPage + startPage;
                    endPage = $scope.list.totalPages;
                }
            } else {
                endPage = endPage - startPage + 1;
                startPage = 1;
            }
        }
        // 分页导航条上的前、后的那三个点
        $scope.beginDote = false;
        $scope.endDote = false;
        if (startPage > 1) {
            $scope.beginDote = true;
        }
        if (endPage < $scope.list.totalPages) {
            $scope.endDote = true;
        }
        // 设置要显示的页号
        for (var i = startPage; i <= endPage; i++) {
            $scope.pageNoList.push(i);
        }
    };

    //是否是当前页
    $scope.isCurrentPage = function (pageNo) {
        if (pageNo == parseInt($scope.searchMap.pageNo)) {
            return true;
        }
        return false;
    }
    //输入页号查询
    $scope.searchByPageNo = function (pageNo) {
        if (parseInt(pageNo) > 0 && parseInt(pageNo) < $scope.list.totalPages) {
            $scope.searchMap.pageNo = pageNo;
           // $scope.searchMap.pageNo=1;

            $scope.search();
        }
    }

    //从首页搜索关键字跳转到搜索页
    $scope.loadSearchKewords = function () {
        $scope.searchMap.keywords = $location.search().keywords;
        $scope.search();
    }
});