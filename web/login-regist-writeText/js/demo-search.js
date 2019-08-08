/**
 * main.js
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2016, Codrops
 * http://www.codrops.com
 */
;(function(window) {

	'use strict';

	var mainContainer = document.querySelector('.main-wrap'),//主页面
		openCtrl = document.getElementById('codrops-header'),//搜索图标
		closeCtrl = document.getElementById('btn-search-close'),//关闭图标
		searchContainer = document.querySelector('.search'),//搜索内容
		inputSearch = searchContainer.querySelector('.search__input');//搜索栏

	function init() {
		initEvents();	
	}

	function initEvents() {
		openCtrl.addEventListener('click', openSearch);
		closeCtrl.addEventListener('click', closeSearch);
		document.addEventListener('keyup', function(ev) {
			// escape key.
			if( ev.keyCode == 27 ) {
				closeSearch();
			}
			if( ev.keyCode == 13 ) {
				alert("hhh");
				$.ajax({
					url:"",
					datatype:"json",
					type:"post",
					data:{
						"serach":$('.search__input').eq(0).val()
					},
					success:function(data){

					},
					error:function(error){
						alert("搜索失败："+error.status);
					}
				})
				
			}
		});
	}

	function openSearch() {
		mainContainer.classList.add('main-wrap--move');
		searchContainer.classList.add('search--open');
		inputSearch.focus();
		$('.main-wrap').css({'overflow':'hidden'})
	}

	function closeSearch() {
		mainContainer.classList.remove('main-wrap--move');
		searchContainer.classList.remove('search--open');
		inputSearch.blur();
		inputSearch.value = '';
		$('#recommend').css({'top':'-20px','opacity':'0'});
		$('.main-wrap').css({'overflow':'auto'})
	}

	init();

})(window);