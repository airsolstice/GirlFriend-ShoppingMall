
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        factory(require('jquery'));
    } else {
        factory(jQuery);
    }
})(function($){
	function Webupload(element,options){
		this.$element = $(element);
			this.$imgList = options.imgBox;
			this.$maxNum = options.maxNum;
			this.$label = options.label;			
			this.urls = options.urls;
		this.init();	
	}
	Webupload.prototype={
		constructor: Webupload,
		init:function(){
			$(this.$element).css('clear','both');
			var options={
			    auto: true,
			    duplicate: true,
			    swf: 'js/Uploader.swf',		
			    server: 'http://cgpublic.wsy.me/index.php/Api/Upload/uploadfile/{'+this.$label+'}',		
			    pick: this.$element,		
			    accept: {
		            title: 'Images',
		            extensions: 'jpg,jpeg,png',
		            mimeTypes: 'image/jpg,image/jpeg,image/png'  
			    }		
			}
			var uploader=WebUploader.create(options);
			var that = this;
			var $sp = $(document.createElement('span'));
			$sp.text(' 图片大小不能超过800k且只能为 jpg,jpeg,png 格式,最多上传'+that.$maxNum+'张 ');
			$sp.css({
				'color':'#aaa',
				'margin-left':'20px',
				'font-size':'12px'
			});
			this.$element.append($sp);
			uploader.on( 'fileQueued', function( file ) {
				layer.load(2);
				if($(that.$imgList).find('div.thumbnail').length>=that.$maxNum){	
					layer.msg('最多只能上传'+that.$maxNum+'张');
					return;
				}	
				if(file.size>=819200){
					layer.msg('图片过大，请换一张');
					return;
				}
			    var $list = $(that.$imgList),
			        $li = $(
			            '<div id="' + file.id + '" class="file-item thumbnail">' +
			                '<img width="100" height="100" class="' + file.id + '">' +
			                '<div class="info">' + file.name + '</div>' +
			            '</div>'
			          ),
			        $btns = $('<div class="file-panel">' +
			                '<span class="cancel">删除</span>' +
			                '<span class="rotateRight">向右旋转</span>' +
			                '<span class="rotateLeft">向左旋转</span></div>').appendTo($li);
			    $list.append( $li );
			    $li.on('mouseenter', function () {
			        $btns.stop().animate({height: 30});
			    });
			
			    $li.on('mouseleave', function () {
			        $btns.stop().animate({height: 0});
			    });	
			    $btns.on('click', 'span', function(){
			        var index = $(this).index(),
			                deg;
			        switch (index) {
			            case 0:
			                //uploader.removeFile(file);
			                $li.off().find('.file-panel').off().end().remove();
			                return;
			            case 1:
			                file.loaded += 90;
			                break;
			            case 2:
			                file.loaded -= 90;
			                break;
			        }
			        deg = 'rotate(' + file.loaded + 'deg)';
			        $('img.'+file.id).css({
			            '-webkit-transform': deg,
			            '-mos-transform': deg,
			            '-o-transform': deg,
			            'transform': deg,
			            'transition':'transform 0.5s'
			            });            
			      });				       
			});
		    uploader.on( 'uploadSuccess', function( file,src,error) {
		    		layer.closeAll();
				$img = $('img.'+file.id);
			    if ( error ) {
			        $img.replaceWith('<span>不能预览</span>');
			        return;
			    }
				$img.attr( 'src', src.data.url );	
		    });
		    if(this.urls!=""){
		    		var $list = $(this.$imgList);
		    		for(var i = 0, $li , $btns ;i < this.urls.length;i++){
			        $li = $(
			            '<div class="file-item thumbnail">' +
			                '<img width="100" height="100" src="'+this.urls[i]+'" class="img-item'+i+'">' +
			            '</div>'
			          ),
			        $btns = $('<div class="file-panel">' +
			                '<span class="cancel">删除</span>' +
			                '<span class="rotateRight">向右旋转</span>' +
			                '<span class="rotateLeft">向左旋转</span></div>').appendTo($li);			    		
				       $list.append($li);
				}
				    $('div.thumbnail').on('mouseenter', function () {
				        $(this).find('div.file-panel').stop().animate({height: 30});
				    });
				
				    $('div.thumbnail').on('mouseleave', function () {
				        $(this).find('div.file-panel').stop().animate({height: 0});
				    });	
				    var $loaded = 0;
				    $('div.file-panel').on('click', 'span', function(){
				        var index = $(this).index(),
				                deg;
				        switch (index) {
				            case 0:
				                //uploader.removeFile(file);
				                $(this).parent().parent().remove();
				                return;
				            case 1:
				                $loaded += 90;
				                break;
				            case 2:
				                $loaded -= 90;
				                break;
				        }
				        deg = 'rotate(' + $loaded + 'deg)';
				        $(this).parent().parent().find('img').css({
				            '-webkit-transform': deg,
				            '-mos-transform': deg,
				            '-o-transform': deg,
				            'transform': deg,
				            'transition':'transform 0.5s'
				            });            
				       });		    		
		    }
		}
	}
	$.fn.webupload = function (option) {
        var args = [].slice.call(arguments, 1);
        return this.each(function () {
            var $this = $(this);
            var fn; 
		 	new Webupload(this, option);
            if (typeof option === 'string' && $.isFunction(fn = data[option])) {
                fn.apply(data, args);
            }
        });
	}
});
//记2016最后一天