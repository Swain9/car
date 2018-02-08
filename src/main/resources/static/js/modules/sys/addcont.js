var vm = new Vue({
    el: '#rrapp',
    data: {
        begin: 0,
        end: 0
    },
    methods: {
        add: function () {
            if (vm.validator()) {
                return;
            }
            var url = "system/contract/add";

            $.ajax({
                type: "POST",
                url: baseURL + url,
                //contentType: "application/json",
                data: {"begin": vm.begin, "end": vm.end},
                success: function (r) {
                    if (r.code === 200) {
                        alert('操作成功');
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        validator: function () {
            var test = /^[1-9]\d{0,5}$/;
            if (parseInt(vm.end) < parseInt(vm.begin)) {
                alert("请填写正确的数字");
                return true;
            }
            if(parseInt(vm.end) - parseInt(vm.begin) > 1000) {
                alert("请填写1000以内的范围");
                return true;
            }
            if (!test.test(vm.begin)) {
                alert("请填写正确的数字");
                return true;
            }
            if (!test.test(vm.end)) {
                alert("请填写正确的数字");
                return true;
            }
        }
    }
});