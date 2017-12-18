$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'users/base/list',
        datatype: "json",
        colModel: [
            {label: '用户ID', name: 'id', index: "id", width: 45, key: true},
            {label: '用户名', name: 'userName', width: 75},
            {label: '邮箱', name: 'userEmail', width: 90},
            {label: '手机号', name: 'userPhone', width: 100},
            {
                label: '用户级别', name: 'userLevel', width: 80, formatter: function (value, options, row) {
                    switch (value) {
                        case '1':
                            return '<span class="label label-success">普通用户</span>';
                        case '2':
                            return '<span class="label label-success">代理商</span>';
                        default:
                            return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {
                label: '用户来源', name: 'userFrom', width: 80, formatter: function (value, options, row) {
                    switch (value) {
                        case '1':
                            return '<span class="label label-success">后台</span>';
                        case '2':
                            return '<span class="label label-success">微信</span>';
                        case '3':
                            return '<span class="label label-success">小程序</span>';
                        default:
                            return '<span class="label label-danger">未知</span>';
                    }
                }
            },
            {
                label: '状态', name: 'isDeleted', width: 80, formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
            {label: '注册时间', name: 'gmtCreate', index: "gmtCreate", width: 80}
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
            username: null
        },
        showList: true,
        title: null,
        disabled: true,
        roleList: {},
        user: {
            isDeleted: 1,
            userLevel: '1',
            roleIdList: []
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.disabled = false;
            vm.user = {
                isDeleted: 1,
                userLevel: '1'
            };

            //获取角色信息
            //this.getRoleList();
        },
        update: function () {
            var userId = getSelectedRow();
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.disabled = true;

            vm.getUser(userId);
        },
        del: function (status) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            var url = baseURL;
            var confirm_message;
            if (status === '1') {
                url += "users/base/enabled";
                confirm_message = "确定要启用选中的用户？";
            } else if (status === '0') {
                url += "users/base/disabled";
                confirm_message = "确定要禁用选中的用户？";
            }

            confirm(confirm_message, function () {
                $.ajax({
                    type: "POST",
                    url: url,
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

            var url = vm.user.id == null ? "users/base/save" : "users/base/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
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
        getUser: function (userId) {
            $.get(baseURL + "users/base/info/" + userId, function (r) {
                vm.user = r.data;
                vm.user.userPwd = null;
                vm.user.userSpwd = null;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'username': vm.q.username},
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            if (isBlank(vm.user.userName)) {
                alert("用户名不能为空");
                return true;
            }

            if (vm.user.id == null && isBlank(vm.user.userPwd)) {
                alert("密码不能为空");
                return true;
            }

            if (isBlank(vm.user.userPhone)) {
                alert("手机不能为空");
                return true;
            }

            if (!validator.isMobilePhone(vm.user.userPhone, "zh-CN")) {
                alert("手机格式不正确");
                return true;
            }
        }
    }
});