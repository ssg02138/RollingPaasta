            function draw(){
                var canvas = document.getElementById("canvas");
                if(canvas.getContext){
                    var ctx = canvas.getContext("2d");  
					
					ctx.strokeStyle = "#36AF12";
					ctx.lineWidth = 4;
					// if문 써서 1이면 #D4171E, 2면 #F8CF3F
					
					ctx.font = "15px Arial";
                    // Draw a Line
					//1구역
                    ctx.fillText("수원광명고속도로",200, 30);
                    ctx.moveTo(280, 0);
                    ctx.lineTo(350, 145);
					// ctx.lineWidth = 15;
					//2구역
					ctx.moveTo(350, 0);
                    ctx.lineTo(250, 155);
					ctx.moveTo(250, 155);
                    ctx.lineTo(185, 220);
					//3구역
					ctx.moveTo(350, 145);
                    ctx.lineTo(402, 255);
					//4구역
                    ctx.fillText("일반국도43호선", 50, 470);
					ctx.moveTo(185, 220);
                    ctx.lineTo(170, 370);
					ctx.moveTo(170, 370);
                    ctx.lineTo(135, 470);
					//5구역
					ctx.moveTo(402, 255);
                    ctx.lineTo(380, 450);
					//6구역
					ctx.moveTo(127, 540);
                    ctx.lineTo(210, 545);
					ctx.moveTo(210, 545);
                    ctx.lineTo(300, 490);
					ctx.moveTo(300, 490);
                    ctx.lineTo(410, 470);
					//7구역
					ctx.moveTo(410, 470);
                    ctx.lineTo(800, 440);
					//8구역
					ctx.moveTo(135, 470);
                    ctx.lineTo(115, 675);
					//9구역
                    ctx.fillText("수도권제2순환고속도로", 300, 560);
					ctx.moveTo(380,450);
                    ctx.lineTo(390,500);
					ctx.moveTo(390,500);
                    ctx.lineTo(470,675);
					
					//10구역
                    ctx.fillText("오산화성고속도로", 800, 600);
					ctx.moveTo(920, 500);
                    ctx.lineTo(810, 675);
                    ctx.stroke();
					
				
					
                    // 수원대학교
                    ctx.beginPath();
                    ctx.arc(520, 320, 40, 0, 2 * Math.PI);
                    ctx.stroke();
					ctx.font = "15px Arial";
                    ctx.fillText("University of Suwon", 510,330);
				
				
					
                }
            }