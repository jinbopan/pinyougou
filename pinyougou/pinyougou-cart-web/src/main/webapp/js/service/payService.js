app.service("payService",function ($http) {
    this.createNative=function (outTradeNo) {
        return $http.get("pay/createNative.do?outTradeNo="+outTradeNo+"&r="+Math.random());
    }
    this.searchPayStatus=function (outTradeNo) {
        return $http.get("pay/searchPayStatus.do?outTradeNo="+outTradeNo+"&r="+Math.random());
    }
})