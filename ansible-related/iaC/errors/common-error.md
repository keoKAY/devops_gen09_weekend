TASK [Ensure the package are installed] ******************************************************************************************************************************
fatal: [localhost]: FAILED! => {"changed": false, "cmd": ["/usr/bin/python3", "-m", "pip.main", "install", "requests", "google-auth"], "msg": "\n:stderr: error: externally-managed-environment\n\n× This environment is externally managed\n╰─> To install Python packages system-wide, try apt install\n    python3-xyz, where xyz is the package you are trying to\n    install.\n    \n    If you wish to install a non-Debian-packaged Python package,\n    create a virtual environment using python3 -m venv path/to/venv.\n    Then use path/to/venv/bin/python and path/to/venv/bin/pip. Make\n    sure you have python3-full installed.\n    \n    If you wish to install a non-Debian packaged Python application,\n    it may be easiest to use pipx install xyz, which will manage a\n    virtual environment for you. Make sure you have pipx installed.\n    \n    See /usr/share/doc/python3.12/README.venv for more information.\n\nnote: If you believe this is a mistake, please contact your Python installation or OS distribution provider. You can override this, at the risk of breaking your Python installation or OS, by passing --break-system-packages.\nhint: See PEP 668 for the detailed specification.\n"}

## ATTEMPT1
```yaml
- name: Install Python packages in a virtual environment
  hosts: your_hosts
  tasks:
    - name: Ensure virtualenv is installed
      apt:
        name: python3-venv
        state: present

    - name: Create virtual environment
      command: python3 -m venv /opt/myenv
      args:
        creates: /opt/myenv

    - name: Install packages in virtualenv
      pip:
        name:
          - requests
          - google-auth
        virtualenv: /opt/myenv
```
## If you unable to instlal google-auth and reqeusts from python
```yaml
- name: Install Python packages system-wide via apt
  hosts: your_hosts
  become: true
  tasks:
    - name: Install required Python packages
      apt:
        name:
          - python3-requests
          - python3-google-auth
        state: present
```