<template>
	<view class="heic-to-png">
		<button class="choose-file" @click="chooseFile()">选择文件</button>
		<view class="file-text">
			<view>已选择文件：</view>
			<view :class="['each-file', {'complany-file': item.status === '下载完成'}]" v-for="(item,index) in choosdFiles" :key="index">
				{{item.fileName}}     <span style="font-size: 10px; margin-left: 15px;">　　{{item.status}}</span>
			</view>
		</view>
		
		<view class="progress-box" v-if="percent != 0">
			<progress :percent="percent" show-info stroke-width="3" />
		</view>
		
		<button class="download-btn" @click="getHeicToJpg()">转换下载</button>
	</view>
</template>

<script>
	import heic2any from 'heic2any'
	export default {
		data() {
			return {
				choosdFiles: [],
				imgData: '',
				percent: 0,
				successNum: 0,
				currentPics: [],
				started: false,
				// 当前正在转换的数量
				currentTrasingSize: 0,
				// 定时器
				interval: null,
				// 当前指针位置
				nowIndex: 0
			};
		},
		watch:{
			nowIndex:{
				handler(val) {
					// 关闭定时器
					console.log('val', val ===  this.choosdFiles.length)
					if (val ===  this.choosdFiles.length) {
						clearInterval(this.interval)
					}
				}
			}
		},
		methods: {
			getHeicToJpg() {
				this.percent = 0
				this.successNum = 0
				this.started = true
				
				this.nowIndex = 0
				this.interval = setInterval(() => {
					console.log('定时触发')
					if (this.currentTrasingSize < 10) {
						this.heicToPng(this.choosdFiles[this.nowIndex])
						this.nowIndex++
					}
				}, 500)
				
			},
			/**
			 * 转换方法
			 * @param {Object} choosdFile
			 */
			heicToPng(choosdFile) {
				console.log('choosdFile', choosdFile)
				uni.showLoading({
				    title: '开始转换',
					mask: true
				});
				this.currentTrasingSize += 1
				choosdFile.status = '正在转换:'
				let xx = heic2any({ blob: choosdFile.file}).then(resultBlob => {
					this.successNum += 1
					this.currentTrasingSize -= 1
					choosdFile.status = '转换完成，正在下载'
					uni.hideLoading();
					this.percent =  Math.round((this.successNum)/this.choosdFiles.length * 100)
					this.saveFile(resultBlob, choosdFile.fileName.substr(0, choosdFile.fileName.length-5) + ".png", choosdFile)
				})
			},
			/**
			 * 选中文件的方法
			 */
			chooseFile() {
				uni.chooseFile({
				  count: 100, //默认100
				  extension:['.heic'],
				    success: (res) => {
						this.choosdFiles = []
						res.tempFiles.forEach(file => {
							this.choosdFiles.push({
								file: file,
								fileName: file.name,
								status: ''
							})
						})
				    }
				});
			},
			listGroup(list) {
				let listGroups = []
				for(let i=0;i<list.length;i+=5){
				    listGroups.push(list.slice(i,i+5));
				}
				return listGroups
			},
			saveFile(blob, filename, res) {
				if (window.navigator.msSaveOrOpenBlob) {
						window.navigator.msSaveOrOpenBlob(blob, filename);
					} else {
						var a = document.createElement("a");
						document.body.appendChild(a);
						var url = window.URL.createObjectURL(blob);
						a.href = url;
						a.download = filename;
						a.click();
						setTimeout(function () {
							window.URL.revokeObjectURL(url);
							document.body.removeChild(a);
							res.status = '下载完成'
						}, 0);
					}
			}
		}
	}
</script>

<style lang="scss">
  .heic-to-png{
	  .choose-file {
		  margin-top: 10px;
		  width: 50%;
		  background-color: #00adb5;
	  }
	  .file-text {
		  color: #2d2d2d;
		  .each-file {
			  background-color: #cccccc;
			  margin-top: 5px;
			  border-radius: 4px;
			  margin-left: 8px;
			  margin-right: 8px;
			  padding-left: 5px;
		  }
		  .complany-file {
			  background-color: #008d00;
		  }
	  }
	  .download-btn {
		  position: fixed;
		  bottom: 0;
		  width: 100%;
		  background-color: #00adb5;
	  }
  }
</style>
