# Generating Manifests for a Three-Layered Application using Helm

This application is divided into 3 layers when deploying to a Kubernetes cluster using Helm charts. Here are the commands to generate the manifests for each layer:

```bash
helm template co-infrastructure ../../charts/co-infrastructure > co-infrastructure-manifests.yaml

helm template platform ../../charts/platform > platform-manifests.yaml

helm template business ../../charts/business > business-manifests.yaml