$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'business/order/list',
        datatype: "json",
        colModel: [
            {label: '编号', name: 'id', index: "id", width: 45, key: true},
            {label: '联系人', name: 'userName', width: 75},
            {label: '手机', name: 'userPhone', width: 90},
            {label: '支付费用', name: 'payMoney', width: 100},
            {
                label: '支付状态', name: 'isPay', width: 100, formatter:
                    function (value, options, row) {
                        return value == 1 ? '<span class="label label-success">已支付</span>' : '<span class="label label-danger">未支付</span>';
                    }
            },
            {label: '返积分账号', name: 'platformId', width: 100},
            {label: '返积分手机号码', name: 'platformPhone', width: 100},
            {label: '返积分平台', name: 'platformName', width: 100}
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
            key: null
        },
        showList: true,
        title: null,
        order: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.order = {
                // contentStatus: 1
            };
        },
        createPool: function(){
            $.get(baseURL + "business/order/pool/create",function (r) {
               console.log(r);
            });
        },
        deletePool: function(){
            $.get(baseURL + "business/order/pool/delete",function (r) {
                console.log(r);
            });
        },
        getContent: function (contentId) {
            $.get(baseURL + "business/order/info/" + contentId, function (r) {
                vm.order = r.data;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key},
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