/**
 * 
 */

 function validar(){
	 let nome = frmProduto.nome.value
	 let fone = frmProduto.descricao.value
	 if (nome === ""){
		 alert("Preencha o campo Nome")
		 frmProduto.nome.focus()
		 return false 
	 } else if (fone === ""){
		 alert("Preencha o campo Descrição")
		 frmProduto.descricao.focus()
		  return false 
	} else {
		document.forms["frmProduto"].submit()
	}
 }