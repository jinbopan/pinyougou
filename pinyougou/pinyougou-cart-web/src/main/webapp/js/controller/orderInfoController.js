app.controller("orderInfoController", function ($scope, addressService, cartService) {
    $scope.findAddressList = function () {
        addressService.findAddressList().success(function (response) {
            $scope.addressList = response;

            //默认显示地址
            for (var i = 0; i < $scope.addressList.length; i++) {
                var address = $scope.addressList[i];
                if ("1" == address.isDefault) {
                    $scope.address = address;
                    break;
                }
            }
        });
    };

    $scope.getUsername = function () {
        cartService.getUsername().success(function (response) {
            $scope.username = response.username;
        });
    };

    //选中地址
    $scope.selectedAddress = function (address) {
        $scope.address = address;
    };

    //判断是否选中
    $scope.isAddressSelected = function (address) {
        if (address == $scope.address) {
            return true;
        }
        return false;
    };


    //支付方式
    $scope.order = {paymentType: '1'};
    $scope.selectByPayType = function (payType) {
        $scope.order.paymentType = payType;
    };


    //显示 购物车数据到支付订单页
    $scope.findCartList = function () {
        cartService.findCartList().success(function (response) {
            $scope.cartList = response;
            //计算总价与购买总件数
            $scope.totalValue = cartService.getTotalValue(response);
        });
    };


    //提交订单
    $scope.subOrder=function () {
        $scope.order.receiverAreaName = $scope.address.address;
        $scope.order.receiverMobile = $scope.address.mobile;
        $scope.order.receiver = $scope.address.contact;
        cartService.subOrder($scope.order).success(function (response) {
            if(response.success){
                if("1"==$scope.order.paymentType){
                    location.href="pay.html#?outTradeNo="+response.message;
                }else {
                    location.href="paysuccess.html";
                }
            }else {
                alert(response.message);
            }
        })
    }
});