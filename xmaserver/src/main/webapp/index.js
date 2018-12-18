
function recorridoRL()
{
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/RL", true, function(response)
        {
            console.log(response);
        });
}

function recorridoLR()
{
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/LR", true, function(response)
        {
            console.log(response);
        });
}

function recorridoUD() {
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/UD", true, function(response)
        {
            console.log(response);
        });
}

function recorridoDU()
{
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/DU", true, function(response)
        {
            console.log(response);
        });
}

function apagarLED() {
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/ApagarLED", true, function(response)
        {
            console.log(response);
        });
}

function encenderLED() {
        var req = new HttpClient();
        req.get("http://localhost:8080/orga/server/Animacion/EncenderLED", true, function(response)
        {
            console.log(response);
        });
}

function apagarDispositivo()
{
    document.getElementById('power').style.color = '#F44949';

    var req = new HttpClient();
    req.get("http://localhost:8080/orga/server/Animacion/ApagarDispositivo", true, function(response)
    {
        console.log(response);
    });
}

function encenderDispositivo()
{
    document.getElementById('power').style.color = '#00FF00';

    var req = new HttpClient();
    req.get("http://localhost:8080/orga/server/Animacion/EncenderDispositivo", true, function(response)
    {
        console.log(response);
    });
}

function dibujar()
{
    var req = new HttpClient();
    req.get("http://localhost:8080/orga/server/Animacion/Dibujar", true, function(response)
    {
        console.log(response);
    });
}

function settings()
{
    var ip = document.getElementById('ip-input').value;
    var puerto = document.getElementById('puerto-input').value;

    if(ip.length != 0 && puerto.length != 0)
    {
        var req = new HttpClient();
        var input = {ip:ip,port:puerto};
        req.post("http://localhost:8080/orga/server/Animacion/Config", true, input, function(response)
        {
            alert(response);
        });
    }
    else
    {
        alert("Rellene los campos");
    }
}


function mostrar()
{
    if(document.getElementById("table-container").style.display == 'none')
    {
      limpiarTabla();
      document.getElementById("table-container").style.display = 'block';
      document.getElementById('power').style.display = 'none';
    }
    else
    {
      document.getElementById("table-container").style.display = 'none';
      document.getElementById('power').style.display = 'block';
    }
}

function enviar_arbol()
{
    document.getElementById("table-container").style.display = 'none';
    document.getElementById('power').style.display = 'block';

    var tabla = document.getElementById('tabla_dibujo');

    var msg = '';

    for(var i = 0; i < tabla.rows.length; i++)
    {
        for(var it = 0; it < tabla.rows.length; it++)
        {
            var color = tabla.rows[i].cells[it].style.background;

            if(color == 'rgb(255, 0, 0) none repeat scroll 0% 0%' || color == 'rgb(255, 0, 0)')
            {
                msg += '0';
            }
            else
            {
                msg += '1';
            }
        }

        //msg += " ";

        var pos = -1;

        for(var it = 0; it < tabla.rows[i].cells.length; it++)
        {
            var color = tabla.rows[i].cells[it].style.background;

            if(color == 'rgb(255, 0, 0) none repeat scroll 0% 0%' || color == 'rgb(255, 0, 0)')
            {
                pos = i;
                break;
            }
        }

        for(var it = 0; it < tabla.rows.length ; it++)
        {
            if(it == pos)
            {
                msg += '1';
            }
            else
            {
                msg += '0';
            }
        }

        //msg += "\n";
    }

    var req = new HttpClient();
    req.post("http://localhost:8080/orga/server/Animacion/Manual", true, msg, function(response)
    {
        console.log(response);
    });

}

function select(i, j)
{
    var tabla = document.getElementById("tabla_dibujo");

    var color = tabla.rows[i].cells[j].style.background;

    if(color == 'rgb(255, 0, 0) none repeat scroll 0% 0%')
    {
      tabla.rows[i].cells[j].style.background = '#BDBDBD';
    }
    else
    {
      tabla.rows[i].cells[j].style.background = 'rgb(255, 0, 0)';
    }
}

function limpiarTabla()
{
    var tabla = document.getElementById("tabla_dibujo");

    for(var i = 0; i < tabla.rows.length; i++)
    {
      for(var j = 0; j < tabla.rows[i].cells.length; j++)
      {
        tabla.rows[i].cells[j].style.background = '#BDBDBD';
      }
    }
}

function getIp()
{
    window.RTCPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new RTCPeerConnection({iceServers:[]}), noop = function(){};
    pc.createDataChannel('');
    pc.createOffer(pc.setLocalDescription.bind(pc), noop);
    pc.onicecandidate = function(ice)
    {
        if (ice && ice.candidate && ice.candidate.candidate)
        {
            var myIP = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/.exec(ice.candidate.candidate)[1];
            document.getElementById('ip-box').innerHTML = myIP;
            pc.onicecandidate = noop;
        }
    };
}
