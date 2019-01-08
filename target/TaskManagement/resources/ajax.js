$(document).ready(
		function() {
			
			$(function() {
			  $(".date").datepicker({
			    dateFormat: 'dd-mm-yyyy',
			    changeMonth: true,
			    changeYear: true
			  });
			});
			var projects = '<option value="">----Select Project----</option>'
			var options=$(project).html();
			options=projects+(options);
			
			var employees = '<option value="">----Select Employees----</option>'
			$('#assignedResources').html(employees);	
				
			$('#project').html(options);	
            $('#project').change(function () {
              	var url=$(valueHolderId).html();
            	$.getJSON(url, {
                	projectId : $(this).val(),
                    ajax : 'true'
                }, function(data) {                
                    if(url.search("tasksbyprojectID")!== -1){
                      $('#mainTable').html('');
                      var len = data.length;
                      for(var i=0; i<len ; i++){
                          var label= $('<td>').text('<b>Task Description </b> : ');
                          var content=$('<td>').text(data[i].taskDesc);
                          var row = $('<tr>').addClass('bar').text(label+content);
                          $('#mainTable').append(row);
                          
                          label= $('<td>').text('Start Date: ');
                          content=$('<td>').text(data[i].startDate);
                          row = $('<tr>').addClass('bar').text(label+content);
                          $('#mainTable').append(row);
                          
                          label= $('<td>').text('End Date : ');
                          content=$('<td>').text(data[i].endDate);
                          row = $('<tr>').addClass('bar').text(label+content);
                          $('#mainTable').append(row);
                          
                          var employees= data[i].assignedResources;
                          var noOfEmployees= employees.length;
                          var employeeTable= $('<table>').addClass()
                          for(var j=0;j<noOfEmployees;j++){
                            var header1= $('<th>').text('MID'); 
                            var header2= $('<th>').text('Employee Name');  
                            row=$('<tr>').text(header1+header2);
                            employeeTable.append(row);
                            
                            var mid=$('<td>').text(employees[j].empId);
                            var employeeName=$('<td>').text(employees[j].empName); 
                            row=$('<tr>').text(mid+employeeName);
                            employeeTable.append(row);  
                          }
                          
                          row=$('<tr>').append($('<tr>').append($('<tr>').text('<hr>')));
                          $('#mainTable').append(row);
                          
                      }    
                    }else{
                    var html = '<option value="">----Select Employees----</option>';
                    var len = data.length;
                    console.log('Call was for Employees);
                                prompt('Call was for Employees');
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].empId + '">' + data[i].empName + '   '+data[i].empId +'</option>';
                    }
                    html += '</option>';

                    $('#assignedResources').html(html);
                    }
                });
            
        });
		});


        