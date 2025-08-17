{{- define "my-deployment.tpl" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .Values.appName.app }}-dpl
spec:
  replicas: {{ .Values.replicas }}
  selector:
    matchLabels:
      app: {{ .Values.appName.app }}
  template:
    metadata:
      labels:
        app: {{ .Values.appName.app }}
    spec:
    # select only all the key:values inside specific key
      nodeSelector: 
      {{- toYaml .Values.nodeSelector | nindent 7 }}
      containers:
      - name: {{ .Values.appName.app }}-cont
      {{- with .Values.container }}
        image: {{ .image }}
        imagePullPolicy: {{ .pullPolicy }}
        ports:
        - containerPort: {{ .port }}
      {{- end }}
      {{- if .Values.volumeMounts }}
        volumeMounts: 
        {{- range  .Values.volumeMounts }}
          - name: {{.name}}
            mountPath: {{.mountPath}} 
        {{- end }}
      {{- end }}
        resources:
          limits:
            memory: "128Mi"
            cpu: "500m"
        env: 
        {{- range .Values.container.envs }}
          - name: {{ .name }}
            value: {{ .value }}
        {{- end}}
    {{- if .Values.volumes }}
      volumes: 
      {{- range .Values.volumes }}
         {{- toYaml . | nindent 7 }}
      {{- end }}
    {{- end }}
{{- end}}