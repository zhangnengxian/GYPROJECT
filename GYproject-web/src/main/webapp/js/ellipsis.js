/**
 * This data rendering helper method can be useful for cases where you have
 * potentially large data strings to be shown in a column that is restricted by
 * width. The data for the column is still fully searchable and sortable, but if
 * it is longer than a give number of characters, it will be truncated and
 * shown with ellipsis. A browser provided tooltip will show the full string
 * to the end user on mouse hover of the cell.
 *
 * This function should be used with the `dt-init columns.render` configuration
 * option of DataTables.
 *
 * It accepts three parameters:
 *
 * 1. `-type integer` - The number of characters to restrict the displayed data
 *    to.
 * 2. `-type boolean` (optional - default `false`) - Indicate if the truncation
 *    of the string should not occur in the middle of a word (`true`) or if it
 *    can (`false`). This can allow the display of strings to look nicer, at the
 *    expense of showing less characters.
 * 2. `-type boolean` (optional - default `false`) - Escape HTML entities
 *    (`true`) or not (`false` - default).
 *
 *  @name ellipsis
 *  @summary Restrict output data to a particular length, showing anything
 *      longer with ellipsis and a browser provided tooltip on hover.
 *  @author [Allan Jardine](http://datatables.net)
 *  @requires DataTables 1.10+
 *
 *	@recodeBy ferrinweb
 *	@描述 则是一个datatable插件的扩展组件，重新编写和新增了方法
 *	
 * @returns {Number} Calculated average
 *
 *  @例1
 *    // 限定一列为17个字符(或汉字)，不要拆分单词(这是对英文单词说的)
 *    $('#example').DataTable( {
 *      columnDefs: [ {
 *        targets: 1,
 *        render: $.fn.dataTable.render.ellipsis( 17, true )
 *      } ]
 *    } );
 *
 *  @例2
 *    // 限定一列为10个字符(或汉字), 可以裁剪单词
 *    $('#example').DataTable( {
 *      columnDefs: [ {
 *        targets: 2,
 *        render: $.fn.dataTable.render.ellipsis( 10 )
 *      } ]
 *    } );
 */

jQuery.fn.dataTable.render.ellipsis = function ( options ) {
	
	options = options === '' || options === undefined ? 18 : options;
	
	if( typeof(options) === "string" || typeof(options) === "number" ){
		/*
		 * 示例参数设置
		 * options = 16
		 */
		//截取长度
		var cutoff = parseInt(options),
		//是否截断单词
		wordbreak = false,
		//是否转义html代码
    	escapeHtml = false,
    	//从字符串后方向前截取
    	fromend = false,
    	//从两边截取, 去掉中间部分
    	cutcenter = false;
    	
	}else{
		/*
		 * 示例参数设置
		 * options = {}
		 * 具体看下方
		 */
    	var cutoff = options.length || 18,
    	wordbreak = options.wordbreak || false,
    	escapeHtml = options.escapeHtml || false,
    	fromend = options.end || false,
    	cutcenter = options.cutcenter || false;
    	cutoff = parseInt(cutoff);
	}
	var esc = function ( t ) {
		return t
			.replace( /&/, '&amp;' )
			.replace( /</, '&lt;' )
			.replace( />/, '&gt;' )
			.replace( /"/, '&quot;' );
	};

	return function ( d, type, row ) {
		// Order, search and type get the original data
		if ( type !== 'display' ) {
			return d;
		}

		if ( typeof d !== 'number' && typeof d !== 'string' ) {
			return d;
		}

		d = d.toString(); // cast numbers

		if ( d.length < cutoff ) {
			return d;
		}
		
		var shortened;
		
		if( fromend ){
			/*
			 * 示例参数设置
			 * options = {
			 *     length: 16,
			 * 	   end: true
			 * }
			 */
			shortened = '&#8230;' + d.substr(1-cutoff, cutoff);
		}else if( cutcenter ){
			var cutstart, cutend;
			if( cutcenter === true ){
				/*
				 * 示例参数设置
				 * options = {
				 *     length: 16,
				 * 	   cutcenter: true
				 * }
				 */
				if( 0 !== cutoff % 2 ){ 
					cutstart = (cutoff - 1) / 2;
					cutend = cutstart + 1;
				}else{
					cutstart = cutend = cutoff / 2;
				}
			}else if( typeof(cutcenter) === "string" || typeof(cutcenter) === "number" ){
				/*
				 * 示例参数设置
				 * options = {
				 *     length: 16,
				 * 	   cutcenter: 7
				 * }
				 */
				cutstart = parseInt(cutcenter);
				cutend = cutoff - parseInt(cutcenter);
			}else{
				/*
				 * 示例参数设置
				 * options = {
				 * 	   cutcenter: {
				 *         start: 10,
				 *         end: 6
				 *     }
				 * }
				 */
				cutstart = cutcenter.start === '' || cutcenter.start === undefined ? 8 : parseInt(cutcenter.start);
				cutend = cutcenter.end === '' || cutcenter.end === undefined ? 8 : parseInt(cutcenter.end);
			}
			var shortenedstart = d.substr(0, cutstart),
			shortenedend = d.substr(1-cutend, cutend);
			shortened = shortenedstart + '&#8230;' + shortenedend;
		}else{
			shortened = d.substr(0, cutoff - 1) + '&#8230;';
		}

		// Find the last white space character in the string
		if ( wordbreak ) {
			shortened = shortened.replace(/\s([^\s]*)$/, '');
		}

		// Protect against uncontrolled HTML input
		if ( escapeHtml ) {
			shortened = esc( shortened );
		}

		return '<span class="ellipsis" data-fullwords="'+esc(d)+'">'+shortened+'</span>';
	};
};
