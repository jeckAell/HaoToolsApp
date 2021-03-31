
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

export {
	http,
	debounce
}