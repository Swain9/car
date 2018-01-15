$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'users/wo/list',
        datatype: "json",
        colModel: [
            {label: '用户ID', name: 'id', index: "id", width: 45, key: true},
            {label: '代理区域', name: 'agentArea', width: 75},
            {label: '联系人', name: 'agentName', width: 90},
            {label: '手机号', name: 'userPhone', width: 100},
            {label: '问题描述', name: 'question', width: 100},
            {label: '处理意见', name: 'msg', width: 100},
            {
                label: '工单类型', name: 'orderType', width: 80, formatter: function (value, options, row) {
                    switch (value) {
                        case '1':
                            return '<span class="label label-success">报单资料</span>';
                        case '2':
                            return '<span class="label label-success">直 通 车</span>';
                        case '3':
                            return '<span class="label label-success">奖金奖励</span>';
                        case '4':
                            return '<span class="label label-success">出彩记录</span>';
                        case '5':
                            return '<span class="label label-success">合同跟踪</span>';
                        case '6':
                            return '<span class="label label-success">积分发放</span>';
                        case '7':
                            return '<span class="label label-success">交车资料</span>';
                        case '8':
                            return '<span class="label label-success">云堂商城</span>';
                        case '9':
                            return '<span class="label label-success">话费油卡</span>';
                        case '10':
                            return '<span class="label label-success">代理底单</span>';
                        case '11':
                            return '<span class="label label-success">报单汇总</span>';
                        default:
                            return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {
                label: '工单状态', name: 'orderStatus', width: 80, formatter: function (value, options, row) {
                    switch (value) {
                        case '1':
                            return '<span class="label label-danger">未处理</span>';
                        case '2':
                            return '<span class="label label-success">已处理</span>';
                        default:
                            return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {label: '提交时间', name: 'gmtCreate', index: "gmtCreate", width: 80},
            {label: '处理时间', name: 'gmtModified', index: "gmtCreate", width: 80}
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
            orderStatus: "",
            orderType: ""
        },
        showList: true,
        title: null,
        disabled: true,
        orderTypeName: null,
        layerTypeOrder: "1",
        hasFile: true,
        order: {}
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

            vm.getOrder(id);
        },
        change: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "更改类型",
                area: ['300px', '200px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#layer_order_type"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var value = {id: id, orderType: vm.layerTypeOrder};
                    $.ajax({
                        type: "POST",
                        url: baseURL + "users/wo/change",
                        contentType: "application/json",
                        data: JSON.stringify(value),
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
                    layer.close(index);
                }
            });

        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.order.id == null ? "users/wo/save" : "users/wo/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.order),
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
        getOrder: function (id) {
            $.get(baseURL + "users/wo/info/" + id, function (r) {
                vm.order = r.data;
                vm.orderTypeName = vm.doOrderType(vm.order.orderType);
                if (isBlank(vm.order.annex)) {
                    vm.hasFile = false;
                } else {
                    vm.hasFile = true;
                }
            });
        },
        doOrderType: function (type) {
            switch (type) {
                case '1':
                    return "报单资料";
                case '2':
                    return "直 通 车";
                case '3':
                    return "奖金奖励";
                case '4':
                    return "出彩记录";
                case '5':
                    return "合同跟踪";
                case '6':
                    return "积分发放";
                case '7':
                    return "交车资料";
                case '8':
                    return "云堂商城";
                case '9':
                    return "话费油卡";
                case '10':
                    return "代理底单";
                case '11':
                    return "报单汇总";
                default:
                    return "未知";
            }
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key, 'orderStatus': vm.q.orderStatus, 'orderType': vm.q.orderType},
                page: page
            }).trigger("reloadGrid");
        },
        download: function () {
            window.open("/users/wo/download/" + vm.order.id);
        },
        validator: function () {
            if (isBlank(vm.order.msg)) {
                alert("处理意见不能为空");
                return true;
            }
        }
    }
});