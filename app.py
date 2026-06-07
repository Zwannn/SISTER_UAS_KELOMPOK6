import subprocess
from flask import render_template, request

from flask import Flask, render_template, request

app = Flask(__name__)

#===============================
# Helper Cek Slot Aktif (RMI/Socket)
#===============================

def get_active_slot():

    # Coba RMI dulu
    try:

        result = subprocess.run(
            [
                "java",
                "-cp",
                "Java",
                "WebCekSlotClient"
            ],
            capture_output=True,
            text=True,
            timeout=3
        )

        output = result.stdout.strip()

        if output.isdigit():

            return output, "RMI"

    except:
        pass

    # Kalau RMI gagal, coba Socket
    try:

        result = subprocess.run(
            [
                "java",
                "-cp",
                "Java",
                "SocketClientCekSlot"
            ],
            capture_output=True,
            text=True,
            timeout=3
        )

        output = result.stdout.strip()

        if "Slot tersedia:" in output:

            slot = output.replace(
                "Slot tersedia:",
                ""
            ).strip()

            return slot, "SOCKET"

    except:
        pass

    return "-", "OFFLINE"





#===============================
# Helper Cek Status Socket Server
#===============================
def get_socket_status():

    try:

        result = subprocess.run(
            [
                "java",
                "-cp",
                "Java",
                "SocketClientCekSlot"
            ],
            capture_output=True,
            text=True,
            timeout=3
        )

        output = result.stdout.strip()

        if "ERROR" in output:
            return "OFFLINE"

        return "ONLINE"

    except:
        return "OFFLINE"
    
#================================
# Helper Ambil Slot Realtime
#================================
def get_slot_realtime():

    try:

        result = subprocess.run(
            [
                "java",
                "-cp",
                "Java",
                "WebCekSlotClient"
            ],
            capture_output=True,
            text=True
        )

        output = result.stdout.strip()

        if output.isdigit():
            return output

        return "-"

    except:
        return "-"
    
    #===============================
    # Helper Cek Status Server  
    #===============================
def get_server_status():

    try:

        result = subprocess.run(
            [
                "java",
                "-cp",
                "Java",
                "WebCekSlotClient"
            ],
            capture_output=True,
            text=True
        )

        output = result.stdout.strip()

        if output.isdigit():
            return "ONLINE"

        return "OFFLINE"

    except:
        return "OFFLINE"

@app.route("/")
def home():

    slot, mode_aktif = get_active_slot()

    rmi_status = get_server_status()

    socket_status = get_socket_status()

    return render_template(
    "index.html",
    slot=slot,
    mode_aktif=mode_aktif,
    rmi_status=rmi_status,
    socket_status=socket_status
)
    
    
    
    
#================================
# Route untuk halaman Gate Masuk
#================================
@app.route("/masuk", methods=["GET", "POST"])
def masuk():

    hasil = None

    if request.method == "POST":

        rfid = request.form["rfid"].strip().upper()

        try:

            result = subprocess.run(
                [
                    "java",
                    "-cp",
                    "Java",
                    "WebMasukClient",
                    rfid
                ],
                capture_output=True,
                text=True
            )

            hasil = result.stdout

            if result.stderr:
                hasil += "\n" + result.stderr

        except Exception as e:

            hasil = f"ERROR: {str(e)}"

    return render_template(
        "masuk.html",
        hasil=hasil
    )
    
    
    
    
    
    
#================================
# Route untuk halaman Gate Keluar
#================================
@app.route("/keluar", methods=["GET", "POST"])
def keluar():

    hasil = None

    if request.method == "POST":

        rfid = request.form["rfid"].strip().upper()

        try:

            result = subprocess.run(
                [
                    "java",
                    "-cp",
                    "Java",
                    "WebKeluarClient",
                    rfid
                ],
                capture_output=True,
                text=True
            )

            hasil = result.stdout.strip()

            if result.stderr:
                hasil += "\n" + result.stderr

        except Exception as e:

            hasil = f"ERROR: {str(e)}"

    return render_template(
        "keluar.html",
        hasil=hasil
    )
    
    
#================================
# Route untuk halaman Concurrent Test
#================================
@app.route("/concurrent", methods=["GET", "POST"])
def concurrent():
    

    hasil_test = []
    slot_akhir = None
    pesan = None

    slot_sekarang = get_slot_realtime()

    if request.method == "POST":

        aksi = request.form.get("aksi")

        if aksi == "test":

            try:

                result = subprocess.run(
                    [
                        "java",
                        "-cp",
                        "Java",
                        "ConcurrentWebTest"
                    ],
                    capture_output=True,
                    text=True
                )

                lines = result.stdout.splitlines()

                for line in lines:

                    if line.startswith("SLOT|"):

                        slot_akhir = line.split("|")[1]

                    elif "|" in line:

                        rfid, status = line.split("|", 1)

                        hasil_test.append({
                            "rfid": rfid,
                            "status": status
                        })

                slot_sekarang = get_slot_realtime()

            except Exception as e:

                pesan = str(e)

        elif aksi == "reset":

            try:

                result = subprocess.run(
                    [
                        "java",
                        "-cp",
                        "Java",
                        "WebResetClient"
                    ],
                    capture_output=True,
                    text=True
                )

                pesan = result.stdout.strip()

                slot_sekarang = get_slot_realtime()

            except Exception as e:

                pesan = str(e)
                

    return render_template(
        "concurrent.html",
        hasil_test=hasil_test,
        slot_akhir=slot_akhir,
        pesan=pesan,
        slot_sekarang=slot_sekarang
    )
    
    
    
    
if __name__ == "__main__":
    app.run(debug=True)
    