$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'system/contract/list',
        datatype: "json",
        colModel: [
            {label: '合同编号', name: 'id', index: "id", width: 45, key: true},
            {label: '订单编号', name: 'orderId', width: 75},
            {label: '联系人', name: 'userName', width: 75},
            {label: '手机号', name: 'userPhone', width: 75},
            {label: '所属区域', name: 'agentName', width: 100},
            {label: '微信昵称', name: 'sendName', width: 100},
            {
                label: '合同状态', name: 'contractStatus', width: 80, formatter: function (value, options, row) {
                    switch (value) {
                        case '1':
                            return '<span class="label label-danger">未提交</span>';
                        case '2':
                            return '<span class="label label-danger">已提交</span>';
                        case '3':
                            return '<span class="label label-success">已通过</span>';
                        default:
                            return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {label: '审核用户', name: 'adminName', width: 80},
            {label: '提交时间', name: 'gmtCreate', width: 100},
            {label: '处理时间', name: 'gmtModified', width: 100}
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
            key: "",
            contractStatus: ""
        },
        title: null,
        disabled: true,
        showList: true,
        contract: {},
        img1: null,
        img2: null
    },
    methods: {
        query: function () {
            vm.reload();
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }

            vm.showList = false;
            vm.title = "审核";

            vm.getContract(id);
        },
        change: function (type) {
            if (vm.validator()) {
                return;
            }
            var url = "system/contract/update";
            if (type == "1") {
                url += "/1"
            } else {
                url += "/0"
            }

            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.contract),
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
        getContract: function (id) {
            $.get(baseURL + "system/contract/info/" + id, function (r) {
                vm.contract = r.data;
                if (isBlank(vm.contract.annex1)) {
                    vm.img1 = null;
                } else {
                    vm.img1 = baseURL + "system/contract/download/1/" + vm.contract.id;
                }
                if (isBlank(vm.contract.annex2)) {
                    vm.img2 = null;
                } else {
                    vm.img2 = baseURL + "system/contract/download/2/" + vm.contract.id;
                }
            });
        },
        showImg: function (type) {
            alert(type);
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key, 'contactStatus': vm.q.contactStatus},
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            return false;
        }
    }
});