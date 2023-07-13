// === アラートメッセージ表示 === //
function messageAlert(recvMSG){if(recvMSG!=null&&recvMSG!="")alert(recvMSG)}


// === 挨拶 === //
function greeting(){
	now = new Date();
	hour=now.getHours();
	greeting=hour<0||hour>23?"時空を超えてのご来場ありがとうございます。":(hour<12?"おはようございます。"+(hour<7?"早":"")+"朝からの":(hour<18?"こんにちは。":"こんばんは。"+(hour<21?"":"夜遅くまで")))+"お仕事お疲れさまです。";
	span=document.getElementsByTagName('span')[0];
	span.innerHTML+=greeting
}


// === ログインページ初期設定 === //
function login(){
	input=document.getElementsByTagName('input');
	for(i=0;i<input.length;i++)input[i].setAttribute('value',i<1?'000005 admin':'Yamagiwa');//開発用。
	for(i=0;i<input.length;i++)input[i].addEventListener('mouseout',function(e){loginCheck()},false);
	setButton('ログイン');
	loginCheck()
}


// === ログインチェック1 === //
function loginCheck(){
	input=document.getElementsByTagName('input');
	button=document.getElementsByTagName('button')[0];
	button.addEventListener('click',function(e){document.forms[0].submit()},false);
	flag=1;
	for(i=0;i<input.length;i++)flag*=input[i].value==''?0:1;
	(flag==1)?button.removeAttribute('style'):button.setAttribute('style','display:none')
}


// === ボタン作成 === //
function setButton(name){
	tag=document.createElement('button');
	tag.setAttribute('style','display:none');
	tag.setAttribute('type','button');
	tag.innerHTML=name;
	document.forms[0].appendChild(tag)
}


// === ログアウト確認用アラート === //
function logout(){if(confirm('ログアウトしますか？'))location.href='Logout.action'}


// === マスタ用実行確認用アラート === //
function execution(message){
	input=document.getElementsByTagName('input');
	if(confirm(message)){
		for(i=3;i<input.length-2;i++)input[i].removeAttribute('disabled');
		document.forms[0].submit()
	}
}


// === 在庫確認画面用初期設定 === //
function stockSet(){
	input=document.getElementsByTagName('input')[0];
	input.addEventListener('change',function(e){addZero(10,0);doExecute('stock_info')},false);
	table=document.getElementsByTagName('table')[0];
	th=document.getElementsByTagName('th');
	td=document.getElementsByTagName('td');
	li=document.getElementsByTagName('li');
	span=document.getElementsByTagName('span');
	li.length>3?li[2].innerHTML=(li[2].innerHTML==''?'':'〒'+li[2].innerHTML.substr(0,3)+'-'+li[2].innerHTML.substr(3)):'';
	li.length>3?li[5].innerHTML=(li[5].innerHTML==''?'':'担当者：'+li[5].innerHTML):'';
	for(i=span.length-2;i<span.length;i++)span.length>2?span[i].innerHTML=(span[i].innerHTML==''?'':(i>span.length-2?'FAX:':'TEL:')+span[i].innerHTML):'';
	for(i=0;i<th.length-1;i++)th[i].addEventListener('click',function(e){conditionSetting([].slice.call(th).indexOf(e.target),1);calculation()},false);//ソート機能。
	for(i=0;i<td.length;i++){
	td[i].innerHTML!=''&&(i%7==1||i%7==4)?td[i].addEventListener('mouseover',function(e){form=document.forms[0];form.company_no.value=e.target.innerHTML;doExecute('company_info')},false):'';
	td[i].innerHTML=(td[i].innerHTML==0&&(i%7==3||i%7==5)?"":td[i].innerHTML)
	}
	calculation()
}


// === 在庫計算 === //
function calculation(){
	td=document.getElementsByTagName('td');
	for(i=7;i<td.length;i++)if(i%7==6)td[i].innerHTML=td[i-7].innerHTML-(-td[i-1].innerHTML)-td[i-3].innerHTML
}


// === ソート条件設定 === //
function conditionSetting(index,start){
	table=document.getElementsByTagName('table')[0];
	th=document.getElementsByTagName('th');
	className=th[index].getAttribute('class');
	className=(className==undefined||className==null||className==''||className=='dn')?'up':'dn';
	for(i=0;i<th.length-1;i++)th[i].removeAttribute('class');
	th[index].setAttribute('class',className);
	sortByTable(start,index,className)
}


// === 非表示設定 === //
function hiddenSet(row){
	input=document.getElementsByTagName('input');
	table=document.getElementsByTagName('table')[0];
	th=document.getElementsByTagName('th');
	start=input[1].value.replace(/-/g,'//');
	stop=input[2].value.replace(/-/g,'//');
	start=start==null||start==''?'/':start;
	stop=stop==null||stop==''?':':stop;
	content=th[[].slice.call(th).indexOf(e.target)].getAttribute('class')=='dn'?'出庫':'入庫';
	for(i=row+1;i<table.rows.length;i++){
		if(table.rows[i].cells[0].innerHTML<start||table.rows[i].cells[0].innerHTML>stop||table.rows[k].cells[column].innerHTML==content)table.rows[i].setAttribute('style','display:none');
		else if(table.rows[i].cells[column].innerHTML!=content)table.rows[i].removeAttribute('style')
	}
	sortByTable(row,0)
}


// === テーブルソート === //
function sortByTable(row,column,order){
	table=document.getElementsByTagName('table')[0];
	k=1;
	flag=true;
	for(i=row+1;i<table.rows.length;i++){
		for(k;k<table.rows.length;k++)if(isNaN(table.rows[i].cells[column].innerHTML))flag=false;
		for(j=i+1;j<table.rows.length;j++)if(table.rows[i].cells[column].innerHTML!=table.rows[j].cells[column].innerHTML&&table.rows[i].getAttribute('style')=='display:none'||table.rows[j].getAttribute('style')!='display:none'&&(flag?table.rows[i].cells[column].innerHTML-table.rows[j].cells[column].innerHTML>0:table.rows[i].cells[column].innerHTML>table.rows[j].cells[column].innerHTML)^order!='dn')table.rows[i].before(table.rows[j])
	}
}


// === 入出庫一覧画面用初期設定 === //
function entryExitSet(){
	input=document.getElementsByTagName('input');
	document.getElementsByTagName('button')[0].addEventListener('click',function(e){document.location.reload()},false);
	for(i=0;i<3;i++)input[i].addEventListener('change',function(e){dateCheck();hiddenSet(0);if(i<1)addZero(10,0);document.forms[0].submit()},false);
	table=document.getElementsByTagName('table')[0];
	th=document.getElementsByTagName('th');
	for(i=0;i<th.length-1;i++)th[i].addEventListener('click',function(e){conditionSetting([].slice.call(th).indexOf(e.target),0)},false)
}


// === マスタ画面用初期設定 === //
function dataSet(){
	body=document.body.id;
	if(body=='user_master')dateConversion(7,'-');
	if(body=='suppplier'||body=='customer_master'){
		zipUtility(6);
		telUtility('tel');
		telUtility('fax')
	}
	if(body=='product_master'){
		addZero(10,3);
		addZero(6,5);
		priceUtility(6,'@');
		priceUtility(7,'\\');
		priceUtility(8,'');
		priceUtility(9,'')
	}
	label=document.getElementsByTagName('label');
	input=document.getElementsByTagName('input');
	textarea=document.getElementsByTagName('textarea')[0];
	textarea.setAttribute('disabled',true);
	choice=document.forms[0].siji.value;
	if(choice=='update')textarea.removeAttribute('disabled');
	for(i=0;i<input.length-2;i++){
		if(i<3)label[i].removeAttribute('class');
 		else input[i].setAttribute('disabled',true);
		input[i].addEventListener(i<3?'click':'change',function(e){selector([].slice.call(input).indexOf(e.target))},false);
		choice=='update'&&i>3?input[i].removeAttribute('disabled'):''
	}
	setButton('確定');
	setButton('キャンセル');
	tag=document.getElementsByTagName('button');
	tag[1].removeAttribute('style');
	for(i=0;i<2;i++)tag[i].addEventListener('click',i>0?function(e){cancel()}:function(e){fix()},false);
	if(choice=='update')check();
	if(choice=='delete')execution('このＩＤを削除しますか？')
}


// === セレクタ === //
function selector(tagNo){
	body=document.body.id;
	label=document.getElementsByTagName('label');
	input=document.getElementsByTagName('input');
	if(tagNo>=0&&tagNo<3){
		demand(tagNo);
		for(i=0;i<input.length-2;i++){
			if(i<3)label[i].removeAttribute('class');
			else tagNo!=0^i<4?input[i].setAttribute('disabled',true):input[i].removeAttribute('disabled')
		}
		textarea=document.getElementsByTagName('textarea')[0];
		tagNo==0?textarea.removeAttribute('disabled'):textarea.setAttribute('disabled',true);
		label[tagNo].setAttribute('class','selected');
		demand(tagNo)
	}
	if(tagNo>3)check();
		switch(tagNo){
		case 0:
			document.forms[0].siji.value='regist';
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			if(body=='product_master')addZero(10,3);
			if(body=='suppplier'||body=='user_master')addZero(6,3);
			document.forms[0].submit();
			break;
		case 4:
			break;
		case 5:
			if(body=='product_master')addZero(6,5)
			break;
		case 6:
			if(body=='product_master')priceUtility(6,'@');
			if(body=='suppplier'||body=='customer_master'){
				zipUtility(6);
				document.forms[0].submit()
			}
			break;
		case 7:
			if(body=='product_master')priceUtility(7,'\\');
			if(body=='user_master')dateConversion(7,'-');
			break;
		case 8:
			if(body=='product_master')priceUtility(8,'');
			break;
		case 9:
			if(body=='product_master')priceUtility(9,'');
			break;
		case 10:
			break;
		case 11:
			break;
		case 12:
			break;
		case 13:
			break;
		case 14:
			break;
		case 15:
			break;
		case 16:
			break;
		case 17:
			break;
		case 18:
			break;
		case 19:
			break;
		case 20:
			break;
		case 21:
			break;
		default:
			break;
	}
}


// === リクエスト処理 === //
function demand(choice){
	label=document.getElementsByTagName('label');
	document.getElementsByTagName('textarea')[0].setAttribute('disabled',true);
	document.getElementsByTagName('button')[0].setAttribute('style','display:none')
	document.forms[0].reset();
	document.forms[0].siji.value=choice>1?'sakujo':choice>0?'kousin':'';
	textarea=document.getElementsByTagName('textarea')[0];
	textarea.setAttribute('disabled',true);
	if(choice<1)textarea.removeAttribute('disabled');
	for(i=0;i<3;i++)label[i].removeAttribute('class');
	label[choice].setAttribute('class','selected');
	for(i=3;i<input.length-2;i++){
		input[i].removeAttribute('value');
		i<4^choice!=0?input[i].setAttribute('disabled',true):input[i].removeAttribute('disabled')
	}
}


// === 入力欄チェック === //
function check(){
	button=document.getElementsByTagName('button');
	input=document.getElementsByTagName('input');
	flag=1;
	for(i=3;i<input.length-2;i++){
		if(i>3){
			flag*=input[i].value!=''?1:0;
			if(flag<1)break
		}
	}
	if(flag==1){
		button[0].removeAttribute('style');
		button[0].setAttribute('class','execution');
		button[1].setAttribute('class','execution')
	}else{
		button[0].setAttribute('style','display:none');
		button[0].removeAttribute('class');
		button[1].removeAttribute('class')
	}
	return flag
}


// === フォーム送信 === //
function fix(){
	for(i=3;i<input.length-2;i++)input[i].removeAttribute('disabled');
	document.getElementsByTagName('textarea')[0].removeAttribute('disabled');
	document.forms[0].submit()
}


// === フォームの初期化 === //
function cancel(){
	input=document.getElementsByTagName('input');
	document.getElementsByTagName('textarea')[0].setAttribute('disabled',true);
	document.getElementsByTagName('button')[0].setAttribute('style','display:none');
	document.forms[0].reset();
	for(i=0;i<input.length-2;i++)if(i<3){
		label[i].removeAttribute('class')
	}else{
		input[i].removeAttribute('value');
		input[i].setAttribute('disabled',true)
	}
}


//=== 数字以外を削除 === //
function removeNonDigits(tagNo){
	toHalfWidth(tagNo);
	input=document.getElementsByTagName('input')[tagNo];
	num=input.value==''?'':input.value;
	num=Number(num.replace(/[^0-9]/g,''));
	input.value=num==''&&num==0?'':num
}


//=== 全角数字半角変換 === //
function toHalfWidth(tagNo){
	input=document.getElementsByTagName('input')[tagNo];
	num=input.value==''?'':input.value;
	input.value='';
	for(i=0;i<num.length;i++)input.value+=(num.charCodeAt(i)>0xff0f&&num.charCodeAt(i)<0xff1a)?String.fromCharCode(num.charCodeAt(i)-0xfee0):String.fromCharCode(num.charCodeAt(i));
}


// === 自動ゼロ詰め === //
function addZero(digits,tagNo){
	removeNonDigits(tagNo);
	input=document.getElementsByTagName('input')[tagNo];
	num=input.value==''?'':input.value;
	zero='';
	for(i=0;i<digits-num.length;i++)zero+='0';
	input.value=num==''?'':zero+num
}


// === 郵便番号フォーマット === //
function zipUtility(tagNo){
	addZero(7,tagNo)
	input=document.getElementsByTagName('input')[tagNo];
	zip=input.value;
	input.value=zip==''?'':zip.slice(0,3)+'-'+zip.slice(3);
}


// === 金額フォーマット === //
function priceUtility(tagNo,prefix){
	removeNonDigits(tagNo);
	input=document.getElementsByTagName('input')[tagNo];
	price=input.value;
	input.value=price==''?'':prefix+String(price).replace(/(\d)(?=(\d\d\d)+(?!\d))/g,'$1,')
}


// === 電話番号変換 === //
function telUtility(name){
	input=document.getElementsByName(name);
	tel=input[0].value.split('-');
	for(i=0;i<3;i++)input[i].setAttribute('value',tel[i]==null||tel[i]==''?'':tel[i])
}


// === 日付フォーマット変換 === //
function dateConversion(tagNo,spacer){
	removeNonDigits(tagNo);
	input=document.getElementsByTagName('input')[tagNo];
	date=input.value;
	input.value=date.slice(0,4)+spacer+date.slice(4,6)+spacer+date.slice(6,8)
}


// === 日付入力欄セッティング === //
function dateCheck(){
	input=document.getElementsByTagName('input');
	day_1=input[1].value;
	day_2=input[2].value;
	day_1!=''?input[2].setAttribute('min',day_1):input[2].removeAttribute('min');
	day_2!=''?input[1].setAttribute('max',day_2):input[1].removeAttribute('max');
	if(day_1!=''&&day_2!=''&&day_1>day_2)alert('日付の設定が間違っています。')
}


// === リンク（nextscr宛て） === //
function scrset(args){
	form=document.forms[0];
	form.nextscr.value=args;
	form.submit()
}


// === フォーム送信（siji付） === //
function doExecute(args){
	form=document.forms[0];
	form.siji.value=args==null?form.siji.value:args;
	form.submit()
}