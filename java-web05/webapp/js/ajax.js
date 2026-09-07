// function ajax(option) {
//     ajax1(option);
// }
//
// function ajax1 (option) {
//     let xmlHttpRequest;
//     if (window.ActiveXObject) { // 检测window中是否存在ActiveXObject这个对象
//         // 微软的IE需要这种方式来获取Ajax核心对象
//         xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP")
//     } else {
//         xmlHttpRequest = new XMLHttpRequest();
//     }
//     xmlHttpRequest.onreadystatechange = function () {
//         // 就绪状态为4的时候表示已经将服务器传输回来
//         if (xmlHttpRequest.readyState === 4) {
//             // HTTP状态码为200的时候，说明该请求处理成功
//             if (xmlHttpRequest.status >= 200 && xmlHttpRequest.status < 300) {
//                 // 这个是服务器端返回回来的结果
//                 let result = xmlHttpRequest.responseText;
//                 if (typeof option.success === 'function') {
//                     // 调用传递进来的函数，这个函数就被称作为回调函数
//                     option.success(result);
//                 }
//             } else {
//                 if (typeof option.error === 'function') {
//                     option.error(xmlHttpRequest.responseText);
//                 }
//             }
//         }
//     };
//     if (option.method.toLowerCase() === "get") {
//         let param = "?";
//         // 获取对象中所有属性名形成的一个集合
//         // {username: admin, password: 123456} => username=admin&password=123456
//         let keys = Object.keys(option.data);
//         keys.forEach(key => {
//             param += key + "=" + option.data[key] + "&";
//         })
//         param = param.substring(0, param.length - 1);
//         option.url += param;
//     }
//     // GET请求发送数据的方式是在URL地址后面进行数据的拼接
//     // true表示异步，false表示同步
//     xmlHttpRequest.open(option.method, option.url, true);
//     if (option.contentType) // 如果ContentType不为NULL，也不为undefined，那么就设置请求头
//         xmlHttpRequest.setRequestHeader("Content-type", option.contentType);
//     let dataInfo = null;
//     if (option.method.toLowerCase() !== "get") {
//         dataInfo = option.data;
//         if (option.contentType && option.contentType.indexOf("application/x-www-form-urlencoded") >= 0) {
//             let param = "";
//             // 获取对象中所有属性名形成的一个集合
//             // {username: admin, password: 123456} => username=admin&password=123456
//             let keys = Object.keys(option.data);
//             keys.forEach(key => {
//                 param += key + "=" + option.data[key] + "&";
//             })
//             param = param.substring(0, param.length - 1);
//             dataInfo = param;
//         }
//     }
//     xmlHttpRequest.send(dataInfo);
// }




function ajax(option) {
    ajax1(option);
}

function ajax1(option) {
    let xmlHttpRequest;

    if (window.ActiveXObject) {
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        xmlHttpRequest = new XMLHttpRequest();
    }

    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState === 4) {
            if (
                xmlHttpRequest.status >= 200 &&
                xmlHttpRequest.status < 300
            ) {
                let result = xmlHttpRequest.responseText;

                if (typeof option.success === "function") {
                    option.success(result);
                }
            } else {
                if (typeof option.error === "function") {
                    option.error(xmlHttpRequest.responseText);
                }
            }
        }
    };

    let parameter = "";

    if (option.data) {
        let keys = Object.keys(option.data);

        keys.forEach(function (key) {
            parameter += encodeURIComponent(key)
                + "="
                + encodeURIComponent(option.data[key])
                + "&";
        });

        if (parameter !== "") {
            parameter = parameter.substring(
                0,
                parameter.length - 1
            );
        }
    }

    if (
        option.method.toLowerCase() === "get" &&
        parameter !== ""
    ) {
        option.url += "?" + parameter;
    }

    xmlHttpRequest.open(option.method, option.url, true);

    if (option.contentType) {
        xmlHttpRequest.setRequestHeader(
            "Content-Type",
            option.contentType
        );
    }

    let dataInfo = null;

    if (option.method.toLowerCase() !== "get") {
        dataInfo = parameter;
    }

    xmlHttpRequest.send(dataInfo);
}
