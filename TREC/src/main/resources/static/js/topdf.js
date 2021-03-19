var jsPDF = window.jspdf.jsPDF;

function saveAsPDF() {
	var doc = new jsPDF('p', 'mm', 'a4');
    var elementHTML = $('#render').html();
  
	margins = {
	        top: 20,
	        bottom: 20,
	        left: 20,
			width: 20
	};
	
    doc.html( elementHTML, {
		callback: function (doc) {
    	doc.save('Prueba.pdf');
   		},
        x: margins.left, // x coord
        y: margins.top, // y coord
        width: margins.width
	});	

//	var doc = new jsPDF();
//	var elementHTML = $('#render').html();
//	doc.setTextColor('0');
//	doc.html(elementHTML, {
//		callback: function (doc) {
//			doc.save('factura.pdf');
//		},
//		fontface: { family: elementHTML.getFontList
//		},
//		x: 10,
//		y: 10
//	})
};

$(document).ready(function($){
	$('#renderAction').click(saveAsPDF);
});