app.service("cartService",function ($http) {
    this.getUsername=function () {
        return $http.get("/cart/getUsername.do?t="+Math.random());
    };
    this.findCartList=function () {
        return $http.get("/cart/findCartList.do?t="+Math.random());
    };

    //增减购物车
    this.addItemToCartList=function (num,itemId) {
        return $http.get("/cart/addItemToCartList.do?t="+Math.random()+"&num="+num+"&itemId="+itemId);
    };

    this.getTotalValue=function (list) {
        var totalValue = {"totalNum":0, "totalMoney":0.0};
        for (var i = 0; i < list.length; i++) {
            var cart = list[i];
            for (var j = 0; j < cart.orderItemList.length; j++) {
                var orderItem=cart.orderItemList[j];
                totalValue.totalNum += orderItem.num;
                totalValue.totalMoney += orderItem.totalFee;
            }
        }
        return totalValue;
    }

    //提交订单
    this.subOrder=function (order) {
        return $http.post("cart/subOrder.do",order);
    }
});