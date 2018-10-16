app.service('searchService',function ($http) {
    this.findAll=function () {
        return $http.post('product/findAll');
    }
    this.findByName=function (entity) {
        return $http.post('product/findByName',entity);
    }
});
