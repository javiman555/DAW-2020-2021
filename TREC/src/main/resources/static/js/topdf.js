var jsPDF = window.jspdf.jsPDF;

function saveAsPDF() {
	
//	html2canvas(document.body,{
//		onrendered: function(canvas){
//		var imgData = canvas.toDataURL('image/png');
//                console.log('Report Image URL: '+ imgData);
//                var doc = new jsPDF('p', 'mm', 'a4');
//                
//                doc.addImage(imgData, 'PNG', 10, 10);
//                doc.save('sample.pdf');
//		}
//	 });

	var doc = new jsPDF('p', 'mm', 'a4');
	var elementHTML = $('#render').html();
	doc.html(elementHTML, {
		fontface: { family: elementHTML.getFontList,
			weight: 1
		},
		callback: function (doc) {
			doc.save('factura.pdf');
		},
		x: 10,
		y: 10
	});

};

$(document).ready(function($){
	$('#renderAction').click(saveAsPDF);
});