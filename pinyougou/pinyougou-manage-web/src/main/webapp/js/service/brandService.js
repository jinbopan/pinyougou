app.service("brandService", function ($http) {
    //查询品牌列表并分页
    this.findPage = function (pageNo, rows) {
        return $http.get("../brand/findPage.do?pageNo=" + pageNo + "&rows=" + rows);
    };
    //添加
    this.add = function (entity) {
        return $http.post("../brand/add.do", entity);
    };
    //根据id查询
    this.findOne = function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    }
    //修改
    this.update = function (entity) {
        return $http.post("../brand/update.do", entity);
    };
    //删除
    this.delete = function (ids) {
        return $http.get("../brand/delete.do?ids=" + ids);
    };
    //按条件查询
    this.search = function (entity, pageNo, rows) {
        return $http.post("../brand/search.do?pageNo=" + pageNo + "&rows=" + rows, entity);
    };
    //查询品牌列表
    this.selectOptionList = function () {
        return $http.post("../brand/selectOptionList.do");
    }
});