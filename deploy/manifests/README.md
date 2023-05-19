# Generating Manifests for a Three-Layered Application using Helm

This application is divided into 3 layers when deploying to a Kubernetes cluster using Helm charts. Here is the command to generate the manifests for whole application:

```bash
helm template the-shoe-shop-1.0 ../../charts > manifests.yaml