
import axios from 'axios'

const http = (url, sendWay, data) => {
	const promise = new Promise((resolve, reject) => {
		// 开启loading，计数发起请求的数量
		loading('+')
		uni.request({
			url: url,
			data: data,
			method: sendWay,
			dataType:'json',
			header:{
				'content-type': 'application/json'
			},
			success: (res) => {
				// 调用接口成功
				loading('-')
				if(res.statusCode === 200) {
					// 返回成功码
					if (res.data.code === '0') {
						resolve(res.data)
					} else {
						// 返回错误码，显示错误信息
						uni.showModal({
						    title: '接口调用错误',
						    content: res.data.message
						})
					}
				} else {
					uni.showModal({
					    title: '错误',
					    content: '系统错误'
					})
				}
			},
			error:function(e) {
				loading('-')
				uni.showModal({
				    title: '错误',
				    content: '网络出错'
				})
				reject('网络出错');
			}
		})
	})
	
	return promise
}

/**
 * 控制loading显示与隐藏的方法。
 * count 为计数器，当count大于0时，显示loading，等于0时，关闭loading
 * option为'-' '+' 分别为计数器进行加减操作
 */
let count = 0
const loading = (option) => {
	if (option === '+') {
		count += 1	
	}
	if (option === '-') {
		count -= 1	
	}
	if (count === 0) {
		uni.hideLoading()
	} else {
		uni.showLoading({
		    title: '加载中'
		})
	}
}

/**
 * 防抖函数
 * fun 要防抖的方法
 * delay 在几毫秒后才能重新调用方法
 */
const debounce = function(fun, delay) {
	let timeTemp = null
	return function() {
		if (timeTemp) {
			clearTimeout(timeTemp)
		}
		timeTemp = setTimeout(fun(), delay)
	}
}

/**
 *  axios的相关配置
 */
// 创建 axios 实例
const requests = axios.create({
  baseURL: '/api', // 基础url,如果是多环境配置这样写，也可以像下面一行的写死。
  timeout: 6000 // 请求超时时间
})
 
// 错误处理函数
// const err = (error) => {
//   if (error.response) {
//     const data = error.response.data
//     // const token = Vue.ls.get(ACCESS_TOKEN)
//     if (error.response.status === 403) {
//         // Notify({ type: 'danger', message: data.message||data.msg });
//     }
//     if (error.response.status === 401) {
//         // Notify({ type: 'danger', message: '你没有权限。' });
//     }
//   }
//   return Promise.reject(error)
// }

// request interceptor(请求拦截器)
// requests.interceptors.request.use(config => {
// //   const token = Vue.ls.get(ACCESS_TOKEN)
//   const token = localStorage.getItem('token')
//   if (token) {
//     config.headers['token'] = token // 让每个请求携带自定义 token 请根据实际情况自行修改
//   }
//   return config
// }, err)

// response interceptor（接收拦截器）
// requests.interceptors.response.use((response) => {
//   const res = response.data
//   if (res.code !== 0&&res.code!==200) { 
//     // Notify({ type: 'danger', message: res.message||res.msg });
//     // 401:未登录;
//     if (res.code === 401||res.code === 403||res.code===999) {
//       Notify({ type: 'danger', message: '请登录'});
//     }
//     return Promise.reject('error')
//   } else {
//     return res
//   }
// }, err)

export {
	http,
	debounce,
	requests
}