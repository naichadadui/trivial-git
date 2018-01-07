
// var table_data = [
//     {
//         id: '5',
//         adminId:'2',
//         content: '最大的哺乳动物',
//         rightAnswer: '蓝鲸'
//     }
var table_data;
    // {
    //     id: '2',
    //     name: 'Rose Parker',
    //     email: 'rose.parker16@example.com',
    //     score: '  (293)-873-2247'
    // },
    // {
    //     id: '3',
    //     name: 'Chloe Nelson',
    //     email: 'chloe.nelson18@example.com',
    //     score: '(957)-213-3499'
    // },
    // {
    //     id: '4',
    //     name: 'Eric Bell',
    //     email: 'eric.bell16@example.com',
    //     score: '(897)-762-9782'
    // }

function loadLog() {

    $(function () {
        $('#table').renderTable();
    });

    ;(function ($, window, document, undefined) {

        var RenderTable = function (elem) {
            var self = this;
            this.table = elem.get(0);
            this.tBody = this.table.tBodies[0];
            this.select_num = 0; // 记录点击值
        };

        RenderTable.prototype = {
            ifAllSelected: function () {
                for (var i = 0; i < this.select_input.length; i++) {
                    if (this.select_num === this.select_input.length - 1) {
                        this.selectAll.checked = true;
                    }
                }
            },

            select: function (index) {
                if (hasClass(this.tBody_rows[index], 'selected-bgColor')) {
                    removeClass(this.tBody_rows[index], 'selected-bgColor');

                    this.select_input[index].checked = false;

                    this.selectAll.checked = false;

                    if (this.select_num > 0) {
                        this.select_num--;
                    } else {
                        this.select_num = 0;
                    }

                    var removeIndex = findOnArray(beSelectedQuestion, document.getElementById("id" + (index + 1)).innerHTML);
                    if (removeIndex !== -1) {
                        beSelectedQuestion.splice(removeIndex, 1);
                    }

                } else {
                    addClass(this.tBody_rows[index], 'selected-bgColor');

                    this.select_input[index].checked = true;

                    this.ifAllSelected();

                    this.select_num++;
                    beSelectedQuestion.push(document.getElementById("id" + (index + 1)).innerHTML);

                    // alert(document.getElementById("id"+(index+1)).innerHTML);
                }
            },

            eventColor: function (index) {
                if (index % 2 === 0) {
                    this.tBody_rows[index].className = 'event-bgColor';
                } else {
                    this.tBody_rows[index].className = 'default-bgColor';
                }
            },

            renderDOM: function () {
                for (var i = 0; i < table_data.length; i++) {
                    var create_tr = document.createElement('tr');


                    var j = 1;
                    for (var property in table_data[i]) {

                        var create_td = document.createElement('td');
                        if (j == 1)
                            create_td.id = "id" + (i + 1);
                        j++;
                        create_td.innerHTML = table_data[i][property];
                        create_tr.appendChild(create_td);
                    }

                    this.tBody.appendChild(create_tr);
                }
            },

            inital: function () {
                this.renderDOM();

                var self = this;
                this.tBody_rows = this.tBody.rows;
                this.selectAll = document.getElementById('selectAll');
                this.select_input = this.tBody.getElementsByTagName('input');

                for (var i = 0; i < this.tBody_rows.length; i++) {
                    this.eventColor(i);

                    this.tBody_rows[i].onmouseover = function () {
                        if (!hasClass(this, 'selected-bgColor')) {
                            addClass(this, 'hover-bgColor');
                        }
                    };

                    this.tBody_rows[i].onmouseout = function () {
                        if (!hasClass(this, 'selected-bgColor')) {
                            removeClass(this, 'hover-bgColor');
                        }
                    };

                    this.tBody_rows[i].onclick = function () {
                        var index = getIndex(this, self.tBody_rows);

                        removeClass(this, 'hover-bgColor');

                        self.select(index);
                    };
                }

                for (var i = 0; i < this.select_input.length; i++) {
                    this.select_input[i].onclick = function () {
                        var index = getIndex(this, self.select_input);

                        self.select(index);
                    };
                }


            },

            constructor: RenderTable
        };

        $.fn.renderTable = function () {
            var renderTable = new RenderTable(this);

            return renderTable.inital();
        };

    })(jQuery, window, document, undefined);

    function addClass(obj, cls) {
        var clsName = obj.className;

        if (clsName) {
            var arr_clsName = clsName.split(" ");
            var iIndex = arrIndexOf(arr_clsName, cls);

            if (iIndex == -1) {
                obj.className += " " + cls;
            }
        } else {
            obj.className = cls;
        }
    };

    function removeClass(obj, cls) {
        if (obj.className) {
            var arr_clsName = obj.className.split(" ");
            var iIndex = arrIndexOf(arr_clsName, cls);

            // 如果里面有这个class
            if (iIndex !== -1) {
                arr_clsName.splice(iIndex, 1);

                obj.className = arr_clsName.join(" ");
            }
        }
    };

    function hasClass(obj, cls) {
        var className = obj.className;

        if (className) {
            var cls_split = className.split(' ');

            if (arrIndexOf(cls_split, cls) === -1) {
                return false;
            } else {
                return true;
            }
        }
    };

    function arrIndexOf(arr, str) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] === str) {
                return i;
            }
        }

        return -1;
    };

    function stopBubble(ev) {
        var oEvent = ev || window.event;

        if (oEvent.stopPropagation) { // 标准
            oEvent.stopPropagation();
        } else { // IE
            oEvent.cancelBubble = true;
        }
    };

    function getIndex(obj, nodes) {
        var result = 0;

        for (var i = 0; i < nodes.length; i++) {
            if (obj === nodes[i]) {
                result = i;
            }
        }

        return result;
    };

    function clickDelete() {

        new $.flavr({
            content: 'Are you sure to delete?',
            dialog: 'confirm',
            onConfirm: function () {

            },
            onCancel: function () {

            }
        });

    }

    function findOnArray(array, aim) {
        for (var i = 0; i < array.length; i++) {
            if (array[i] == aim) {
                return i;
            }
        }
    }
}

function searchByAdminId(){
    var sAdminId = $("#adminIdS").val();

    $.ajax(
        {
            type: "post",
            url: "/api/homepage/admin/searchAdminLogBySearchKey",
            timeout: 8000,
            dataType: "json",
            data: {
                "searchId": sAdminId
            },

            success: function (data) {
                if (data) {
                    $('#logTbody').empty();
                    table_data = eval("(" + data.searchAdminLogs + ")");
                    loadLog();
                }
            },

            error: function () {
                alert("404!!");

            }
        })
}

