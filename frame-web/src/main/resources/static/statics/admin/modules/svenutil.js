/**
 * 工具，拓展util
 *
 * @author pswen/3197544360@qq.com
 */

;layui.define(["util"], function (exports) {
    var obj = {
        /**
         * 获取路由的参数
         * @param key 路由键名
         * @returns {string|boolean} 键值或者false
         */
        getUrlParam: function (key) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] === key) {
                    return pair[1];
                }
            }
            return (false);
        }
    };
    //输出svenutil接口
    exports('svenutil', obj);
});