$(document).ready(function() {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/CTA/Login?OPERATION_TYPE=GET_DISTINCT_COURSE_LIST",
		success : fnDropdownDataFetched,
		error : fnDataFailure
	});
});


var i=1;
var assignmentCount = 1, quizCount = 1, examCount = 1, projectCount = 1, othersCount = 1;
var count;

var Quiz="";
var Project = "";
var Assignment = "";
var Exam = "";



function fnDropdownDataFetched(response) {
console.log(response);
// response.data[0]
var res_array = response.LOGIN_RESPONSE.response.dcdata;
for (var i = 0; i < res_array.length; i++) {
	var c_id = res_array[i].c_id;
	var dropdownText = res_array[i];
	$('#course_id').append(
			"<option value='" + dropdownText + "'>" + dropdownText + "</option>");
}
}

function fnDataFailure(response) {
	alert(response);
}

 var gradDet = {
		 
  add: function(type, divId)
  {
	  if(type == 'Quiz'){
		  i = quizCount;
	  }
	  else if(type == 'Assignment'){
		  i = assignmentCount;
	  }
	  else if(type == 'Exam'){
		  i = examCount;
	  }
	  else if(type == 'Project'){
		  i = projectCount;
	  }
	  else if(type == 'Others'){
		  i = othersCount;
		  type = $('#othersId').val();
	  }
	  if ( $('#'+divId).children().length == 0 ) {
		     // do something
		  i=1;
		}
	  
	var name = type + i;
    var textTag = "<input type='text' size='2' id='text" + name + "' name = 'text" +name+"' class='text"+ type+"' />"; 
    $('#'+divId).append( "<div id = '"+name+"' name = '"+name+"'>"+name +""+ ":&nbsp"+ textTag + "&nbsp" + "" +
    		"<img width = '15' src ='remove.ico' id = "+name+" onClick = \"gradDet.removeElement('"+name+"','"+type+"');\">" );
    i++;
    if(type == 'Quiz'){
		  quizCount = i;
	  }
	  else if(type == 'Assignment'){
		  assignmentCount = i;
	  }
	  else if(type == 'Exam'){
		  examCount = i;
	  }
	  else if(type == 'Project'){
		  projectCount = i;
	  }
	  else if(type == $('#othersId').val()){
		  othersCount = i;
	  }
    count = i;
    $('#quizCount').attr("value",quizCount-1);
    $('#assignmentCount').attr("value",assignmentCount-1);
    $('#projectCount').attr("value",projectCount-1);
    $('#examCount').attr("value",examCount-1);
    $('#othersCount').attr("value",othersCount-1);
  },
 isCheckedById: function (id) {
	 var checkBox = document.getElementById(id);
		  if (checkBox.checked == true) {
	    return true;
	  } else {
	    return false;
	  }
},
  onChangeCheck: function(id, buttonID, textID, type){
	  var otherType;
	  var val =$('#' + id).is(":checked");
	  if(type == 'Others'){
		  type = $('#othersId').val();
		  otherType = 'Others';
	  }
	  if(val == true){
		  if(otherType == 'Others'){
			  $('#othersId').attr( "hidden", false );
		  }
		  $('#' + buttonID).attr( "hidden", false );
		  $('#' + textID).attr( "hidden", false );
		  for(j = 1; j<count; j++){
			  $('#' +type+j).attr("hidden", false);
		  }
	  }
	  else{
		  if(type == 'Others'){
			  type = $('#othersId').attr( "hidden", true );
		  }
		  $('#' + buttonID).attr( "hidden", true );
		  $('#' + textID).attr( "hidden", true );
		  for(j = 1; j<count; j++){
			  $('#' +type+j).attr("hidden", true);
		  }
	  }
	  
  },
  removeElement: function(name, type){
	  $('#' +name).remove();
	  if(type == 'Quiz'){
		  --quizCount;
	  }
	  else if(type == 'Assignment'){
		  --assignmentCount;
	  }
	  else if(type == 'Exam'){
		  --examCount;
	  }
	  else if(type == 'Project'){
		  --projectCount;
	  }
	  else if(type == $('#othersId').val()){
		  --othersCount;
	  }
  },
  validate: function(){
	  
	var sumQuiz = parseInt(0), sumAssignment = parseInt(0), sumExam = parseInt(0), sumOthers = parseInt(0), sumProject = parseInt(0);
	var quizTotal = parseInt($('#quizText').val()?$('#quizText').val():0);
	var assignmentTotal = parseInt($('#assignmentText').val()?$('#assignmentText').val():0);
	var examTotal = parseInt($('#examText').val()?$('#examText').val():0);
	var projectTotal = parseInt($('#projectText').val()?$('#projectText').val():0);
	var othersTotal = parseInt($('#othersText').val()?$('#othersText').val():0);
	var total = quizTotal + assignmentTotal + examTotal + projectTotal + othersTotal;
	for(k = 1 ; k < quizCount ; k++){
		valType = 'textQuiz'+k;
		sumQuiz += parseInt($('#'+valType).val());
	}
	for(k = 1 ; k < assignmentCount ; k++){
		valType = 'textAssignment'+k;
		sumAssignment += parseInt($('#'+valType).val());
	}
	for(k = 1 ; k < projectCount ; k++){
		valType = 'textProject'+k;
		sumProject += parseInt($('#'+valType).val());
	}
	for(k = 1 ; k < examCount ; k++){
		valType = 'textExam'+k;
		sumExam += parseInt($('#'+valType).val());
	}
	for(k = 1 ; k < othersCount ; k++){
		valType = 'text'+k;
		sumOthers += parseInt($('#'+valType).val());
	}
	alert(quizTotal+":"+sumQuiz+"---"+assignmentTotal+":"+sumAssignment+"---"+projectTotal+":"+sumProject+"---"+examTotal+":"+sumExam+"---"+othersTotal+":"+sumOthers);
	if ((quizTotal != sumQuiz) || (assignmentTotal != sumAssignment) || (projectTotal != sumProject) || (examTotal != sumExam)){
		alert("The individual percentage in each test type should addup to the total value");
	}
	else if(total != 100){
		alert("The total percentage doesn't add up to 100")
		
	}
	
	$('.textQuiz').each(function () {
        Quiz = Quiz + (this.value);
        Quiz = Quiz + (",");
    });
	$('.textProject').each(function () {
        Project = Project + (this.value);
        Project = Project + (",");
    });
	$('.textAssignment').each(function () {
        Assignment = Assignment + (this.value);
        Assignment = Assignment + (",");
    });
	$('.textExam').each(function () {
        Exam = Exam + (this.value);
        Exam = Exam + (",");
    });
	
	console.log(Quiz);
	 $('#quizarray').attr("value",Quiz);
	 $('#assignmentarray').attr("value",Assignment);
	 $('#projectarray').attr("value",Project);
	 $('#examarray').attr("value",Exam);
/*	$.ajax({
		  url: "GradingDetails",
		  type: 'POST', 
		  data: {data: "swathi"},
		  success: function(){
		        alert("  success  ");
		     },
		  error: function(){
		        alert("  success  ");
		     }
		});*/
  },
};
 