app.service("searchService",function ($http) {
    this.searchService=function (searchMap) {
        return $http.post("search/itemSearch.do",searchMap);
    }
})