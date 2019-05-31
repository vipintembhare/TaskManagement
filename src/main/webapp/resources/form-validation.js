// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='assignTask']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
    	assignedResources: {required: true,
    		valueNotEquals:"default"
    		},
      taskDesc: "required",
      startDate: {
        required: true
      },
      endDate: {
        required: true,
        greaterThan: "#startDate"
      },
      project:{valueNotEquals:"default" }
    },
    // Specify validation error messages
    messages: {
    	taskDesc: "Please enter your task description",
      startDate: "Please enter start date",
      endDate: {
        required: "Please enter due date",
        greaterThan: "End Date should be greater than StartDate"
      },
      assignedResources: {required: "Please select resources",
    	  valueNotEquals: "Please select an project"},
      project: { valueNotEquals: "Please select an project" }
      
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});

// add the rule here
$.validator.addMethod("valueNotEquals", function(value, element, arg){
 return arg !== value;
}, "Value must not equal arg.");

$.validator.addMethod("greaterThan", 
function(value, element, params) {

    if (!/Invalid|NaN/.test(new Date(value))) {
        return new Date(value) > new Date($(params).val());
    }

    return isNaN(value) && isNaN($(params).val()) 
        || (Number(value) > Number($(params).val())); 
},'Must be greater than {0}.');