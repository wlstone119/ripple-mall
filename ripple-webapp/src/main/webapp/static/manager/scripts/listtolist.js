/**
fromid:Դlist��id.
toid:Ŀ��list��id.
moveOrAppend����("move"������"append"):
move -- Դlist��ѡ�е�option��ɾ��.Դlist��ѡ�е�option�ƶ���Ŀ��list��,��Ŀ��list���Ѵ������option�����.
append -- Դlist��ѡ�е�option����ɾ��.Դlist��ѡ�е�option��ӵ�Ŀ��list�ĺ���,��Ŀ��list���Ѵ������option�����.

isAll����(true����false):�Ƿ�ȫ���ƶ������
*/
jQuery.listTolist = function(fromid,toid,moveOrAppend,isAll) {
	if(moveOrAppend.toLowerCase() == "move") {	//�ƶ�
		if(isAll == true) {	//ȫ���ƶ�
			$("#"+fromid+" option").each(function() {
				//��Դlist�е�option��ӵ�Ŀ��list,��Ŀ��list�����и�optionʱ�����κβ���.
				$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
			$("#"+fromid).empty();	//���Դlist
		}
		else if(isAll == false) {
			$("#"+fromid+" option:selected").each(function() {
				//��Դlist�е�option��ӵ�Ŀ��list,��Ŀ��list�����и�optionʱ�����κβ���.
				$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
				//Ŀ��list���Ѿ����ڵ�option��û���ƶ�,�Ծ���Դlist��,�������.
				if($("#"+fromid+" option[value="+$(this).val()+"]").length > 0) {
					$("#"+fromid).get(0)
					.removeChild($("#"+fromid+" option[value="+$(this).val()+"]").get(0));
				}
			});
		}
	}
	else if(moveOrAppend.toLowerCase() == "append") {
		if(isAll == true) {
			$("#"+fromid+" option").each(function() {
				$("<option></option>")
				.val($(this).val())
				.text($(this).text())
				.appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
		}
		else if(isAll == false) {
			$("#"+fromid+" option:selected").each(function() {
				$("<option></option>")
				.val($(this).val())
				.text($(this).text())
				.appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
		}
	}
};
/**
���ܴ���ͬ��("move").
��֮ͬ�����ڵ�Դlist�е�ѡ��option��Ŀ��list�д���ʱ,Դlist�е�option����ɾ��.

isAll����(true����false):�Ƿ�ȫ���ƶ������
*/
jQuery.list2list = function(fromid,toid,isAll) {
	if(isAll == true) {
		$("#"+fromid+" option").each(function() {
			$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
		});
	}
	else if(isAll == false) {
		$("#"+fromid+" option:selected").each(function() {
			$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
		});
	}
};