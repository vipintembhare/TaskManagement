$(document).ready(
		function() {
			
			var projects = '<option value="default">----Select Project----</option>'
			var options=$(project).html();
			options=projects+(options);
			
			var employees = '<option value="default">----Select Employees----</option>'
			$('#assignedResources').html(employees);	
			$("#startDate").datepicker({ dateFormat: 'dd-mm-yy' });
		    $("#endDate").datepicker({ dateFormat: 'dd-mm-yy' });
		    
			$('#project').html(options);	
            $('#project').change(function () {
              	var url='/TaskManagement/assignTasks/getEmployees';
            	$.getJSON(url, {
                	projectId : $(this).val(),
                    ajax : 'true'
                }, function(data) {                
                    
                    var html = '<option value="default">----Select Employees----</option>';
                    var len = data.length;
                              
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].empId + '">' + data[i].empName + '   '+data[i].empId +'</option>';
                    }
                    html += '</option>';

                    $('#assignedResources').html(html);
                    
                });
            	
            	
            	
            
        });
            
		});


        