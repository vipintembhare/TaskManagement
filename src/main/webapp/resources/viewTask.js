$(document).ready(
		function() {
			
			var projects = '<option value="">----All Project----</option>'
			var options=$(project).html();
			options=projects+(options);
			
			$('#project').html(options);
			            $('#project').change(function () {
              	var url='/TaskManagement/show/tasksbyprojectID';
            	$.getJSON(url, {
                	projectId : $(this).val(),
                    ajax : 'true'
                }, function(data) {                
                    
                     $('#tableDiv').html('<br><table id="mainTable"/>');
                     $('#tableDiv').attr('style','left: 300px');
                      $('#mainTable').html('');
                      $.each(data, function(i, task) {
                    	  var $tr = $('<tr>').append(
                                  $('<td>').text('Project : '+task.project.projectName).css("font-weight","bold")
                              ).appendTo('#mainTable');
                               var $tr = $('<tr>').append(
                                  $('<br>') ).appendTo('#mainTable'); 

                          $tr = $('<tr>').append(
                              $('<td>').text('Task Description : '),
                              $('<td>').text(task.taskDesc)
                          ).appendTo('#mainTable');
                          
                          $tr = $('<tr>').append(
                                  $('<td>').text('Task Start Date : '),
                                  $('<td>').text(task.startDate)
                              ).appendTo('#mainTable');
                              
                              $tr = $('<tr>').append(
                                      $('<td>').text('Task End Date :'),
                                      $('<td>').text(task.endDate)
                                  ).appendTo('#mainTable');
                                  
                                  var employees= task.assignedResources;
                                  var employeeTable=  $('<table>').addClass('addBorder').css("border","thin solid black").attr('colspan',2);
                                   var trs = $('<tr>').append(
                                          $('<th>').text('MID').css('border', ' thin solid black'),
                                          $('<th>').text('Employee Name').css('border', ' thin solid black')
                                      );
                                   employeeTable.append(trs).css('border', ' thin solid black');
                                  $.each(employees, function(j, emp) {
                                	  var trs = $('<tr>').append(
                                              $('<td>').text(emp.empId).css('border', 'thin solid black'),
                                              $('<td>').text(emp.empName).css('border', 'thin solid black')
                                          );
                                	  employeeTable.append(trs);
                                  });
                                  console.log($(employeeTable).html());
                                   $('#tableDiv').append($('#mainTable').html());
                                   $('#mainTable').html('');
                                  $('#tableDiv').append(employeeTable); 
                                     $('#tableDiv').append( '<br><br><hr id="runningLine">');
                                  console.log( $('#mainTable').html());
                });
            	
            	
                      					$("#tableDiv").css({"border": " thin solid","width":300,"border-bottom": "none","margin-top":25,"text-align": "center"});
										$("#mainTable").css("margin",10);
										$("#runningLine").css("color","black");

            
        });
		});

		});

function style() {
    $("h1").css("align", "center");
    $("#empTable").css( "border", "thin single black collapse");
    $(".addBorder").css("border", "thin single black collapse");
}
        