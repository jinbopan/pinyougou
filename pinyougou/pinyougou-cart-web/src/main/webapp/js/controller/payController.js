app.controller("payController", function ($scope, $location, payService, cartService) {
    $scope.getUsername = function () {
        cartService.getUsername().success(function (response) {
            $scope.username = response.username;

        });
    };

    $scope.createNative = function () {
        $scope.outTradeNo = $location.search()["outTradeNo"];
        payService.createNative($scope.outTradeNo).success(function (response) {
            //成功返回后生成二维码
            if ("SUCCESS" == response.result_code) {
                $scope.total_fee = (response.totalFee / 100).toFixed(2);
                var qrious = new QRious({
                    element: document.getElementById("qrious"),
                    level: "H",
                    size: 250,
                    value: response.code_url
                });

                //生成二维码成功能后查询支付状态
                searchPayStatus($scope.outTradeNo);
            } else {
                alert("生成二维码失败");
            }

        });
    }

    searchPayStatus = function (outTradeNo) {
        payService.searchPayStatus(outTradeNo).success(function (response) {
            if (response.success) {
                location.href = "paysuccess.html#?money="+ $scope.total_fee;
            } else {
                if ("生成二维码超时" == response.message) {
                    //重新发送请求生成二维码
                    $scope.createNative();
                } else {
                    location.href = "payfail.html";
                }
            }
        })
    }

    $scope.getMoney = function () {
        $scope.money = $location.search()["money"];
    }
});