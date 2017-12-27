// var table_data = [
//     {
//         id: '1',
//         name: '嘿嘿',
//         score: '100$',
//     },
//     {
//         id: '2',
//         name: '闪闪',
//         score: '70$',
//     },
//     {
//         id: '3',
//         name: 'SUNNY',
//         score: '30$',
//     },
//     {
//         id: '4',
//         name: 'EricBell',
//         score: '70$',
//     }
// ];

var table_data;

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

                for (var property in table_data[i]) {
                    var create_td = document.createElement('td');

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

            this.selectAll.onclick = function () {
                for (var i = 0; i < self.tBody_rows.length; i++) {
                    if (this.checked) {
                        addClass(self.tBody_rows[i], 'selected-bgColor');
                        self.select_input[i].checked = true;

                        self.select_num = self.tBody_rows.length;
                    } else {
                        removeClass(self.tBody_rows[i], 'selected-bgColor');
                        self.select_input[i].checked = false;

                        self.select_num = 0;
                    }
                }
            };
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

function initRanking(data){
    table_data=eval ("(" + data + ")");
}

