var jsPDF = window.jspdf.jsPDF;

function saveAsPDF() {
	
	// COMENTAR EL CÓDIGO
	
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
//	var elementHTML = $('#render').html();
	var dataFacturation = [document.getElementsByClassName('firstname')[0].innerHTML,
		document.getElementsByClassName('surname')[0].innerHTML,
		document.getElementsByClassName('address')[0].innerHTML,
		document.getElementsByClassName('postalcode')[0].innerHTML,
		document.getElementsByClassName('city')[0].innerHTML,
		document.getElementsByClassName('country')[0].innerHTML]
//	console.log(dataFacturation);

	
	var dataFactFieldNames = ["Nombre", "Apellidos", "Dirección", "Código Postal", "Ciudad", "País"]	
	var numDishes = 0;
	var list1 = document.getElementsByClassName('dishname'),
    dishNames = [];
	for (var i = 0; i < list1.length; i++) {
	    dishNames.push(list1[i].textContent);
		numDishes++;
	}
//	console.log(dishNames);

	var list2 = document.getElementsByClassName('dishprice'),
    dishPrices = [];
	for (var i = 0; i < list2.length; i++) {
	    dishPrices.push(list2[i].textContent);
	}
//	console.log(dishPrices);
	
	var price = document.getElementsByClassName('price')[0].innerHTML;
//	console.log(price);
	
	doc.setFont = doc.getFontList();
//	console.log(doc.getFontList());
	doc.setFontSize(16);
	doc.text(15, 15, "Datos de facturación");
	doc.setFontSize(11);
	doc.setFontType = ("bold");
	doc.text(55, 22, dataFactFieldNames, "right");
	doc.setFontType = ("normal");
	doc.text(58, 22, dataFacturation);
	
	doc.addImage("/images/logo.png", "PNG", 180, 10, 20, 20);
	
	doc.setFontSize(16);
	doc.text(15, 55, "Resumen de pedido");
	doc.setFontSize(11);
	doc.setFontType = ("bold");
	doc.text(55, 62, "Plato", "right");
	doc.text(58, 62, "Importe");
	doc.setFontType = ("normal");
	doc.text(55, 68, dishNames, "right");
	doc.text(58, 68, dishPrices);
	doc.setFontType = ("bold");
	doc.text(35, 68 + 8*numDishes, "Precio total: " + price + "0€");
	
	
	
	doc.save('factura.pdf')
//	doc.html(elementHTML, {
//		fontface: { family: elementHTML.getFontList,
//			weight: 1
//		},
//		callback: function (doc) {
//			doc.save('factura.pdf');
//		},
//		x: 10,
//		y: 10
//	});

};

$(document).ready(function($){
	$('#renderAction').click(saveAsPDF);
});