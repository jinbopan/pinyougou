app.controller("cartController", function ($scope, cartService) {
    $scope.getUsername=function(){
        cartService.getUsername().success(function (response) {
            $scope.username = response.username;

        });
    };


    $scope.oldCartList={};
    $scope.findCartList=function(){
        cartService.findCartList().success(function (response) {
            $scope.cartList=response;
            $scope.oldCartList=JSON.parse(JSON.stringify($scope.cartList));
            //计算总价与购买总件数
           $scope.totalValue= cartService.getTotalValue(response);
        });
    };

    $scope.addItemToCartList=function(num,itemId){
        cartService.addItemToCartList(num,itemId).success(function (response) {
            if(response.success){
                $scope.findCartList();
            }else {
                alert(response.message);
            }
        });
    };

    $scope.myKeyup = function(e,num,itemId){
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            var newNum=parseInt(num);
            if(newNum<=0){
                alert("输入的购买数量不能小于1");
                return;
            }
            newNum=newNum-getOldNum(itemId);
            $scope.addItemToCartList(newNum,itemId);
            $scope.findCartList();
        }
    };

    //手动输入时，
    getOldNum=function (itemId) {
        for (var i = 0; i < $scope.oldCartList.length; i++) {
            var cart = $scope.oldCartList[i];
            for (var j = 0; j < cart.orderItemList.length; j++) {
                var orderItem = cart.orderItemList[j];
                if(orderItem.itemId==itemId){
                    return orderItem.num;
                }
            }
        }
    }


});