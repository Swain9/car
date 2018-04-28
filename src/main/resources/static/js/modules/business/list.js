$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'business/content/list',
        datatype: "json",
        colModel: [
            {label: '活动ID', name: 'id', index: "id", width: 45, key: true},
            {label: '主题', name: 'contentTheme', width: 75},
            {label: '讲师', name: 'contentTeacher', width: 90},
            {label: '报名费', name: 'contentMoney', width: 100},
            {label: '活动说明', name: 'contentDetails', width: 80},
            {label: '缴费人数', name: 'contentPayingCount', width: 80},
            {label: '总收费用', name: 'contentSigninCount', width: 80},
            {label: '到场人数', name: 'contentSigninCount', width: 80},
            {label: '开启时间', name: 'beginTime', width: 80},
            {label: '结束时间', name: 'endTime', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "rows",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            theme: null
        },
        showList: true,
        title: null,
        content: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.content = {
                contentStatus: 1
            };
        },
        update: function () {
            var contentId = getSelectedRow();
            if (contentId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getContent(contentId);
        },
        del: function (status) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            if (status === '1') {
            } else if (status === '0') {
            }

            confirm("确定要删除吗", function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "business/content/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code === 200) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.content.id == null ? "business/content/save" : "business/content/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.content),
                success: function (r) {
                    if (r.code === 200) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getContent: function (contentId) {
            $.get(baseURL + "business/content/info/" + contentId, function (r) {
                vm.content = r.data;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'theme': vm.q.theme},
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            if (isBlank(vm.content.contentTheme)) {
                alert("主题不能为空");
                return true;
            }
            if (isBlank(vm.content.contentTeacher)) {
                alert("讲师不能为空");
                return true;
            }
            if (parseFloat(vm.content.contentMoney) <= 0) {
                alert("请填写正确的金额");
                return true;
            }

        }
    }
});